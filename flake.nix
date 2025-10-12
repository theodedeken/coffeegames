{
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-24.11";
    flake-utils.url = "github:numtide/flake-utils";
    # theovim.url = "github:theodedeken/theovim";
    theovim.url = "path:/home/theo/PROJECTS/theovim";
    rust-overlay = {
      url = "github:oxalica/rust-overlay";
      inputs.nixpkgs.follows = "nixpkgs";
    };
  };
  outputs = {
    self,
    nixpkgs,
    flake-utils,
    rust-overlay,
    ...
  } @ inputs:
    flake-utils.lib.eachDefaultSystem
    (
      system: let
        overlays = [(import rust-overlay) inputs.theovim.overlays.default];
        pkgs = import nixpkgs {
          inherit system overlays;
        };
        rust = pkgs.rust-bin.stable.latest.default.override {
          extensions = [
            "rust-src" # for rust-analyzer
            "rust-analyzer"
          ];
        };
      in
        with pkgs; {
          devShells.default = mkShell rec {
            nativeBuildInputs = [
              pkg-config
            ];
            packages = [
              bazel_7
              rust
              python310
              (
                theovim.extend {
                  theovim.lang.java.enable = true;
                  plugins.jdtls.settings.settings.java.configuration.runtimes = [
                    {
                      name = "JavaSE-21";
                      path = jdk;
                      default = true;
                    }
                  ];
                }
              )
              jdk
            ];
            buildInputs = [
              udev
              alsa-lib
              vulkan-loader
              xorg.libX11
              xorg.libXcursor
              xorg.libXi
              xorg.libXrandr # To use the x11 feature
              libxkbcommon
              wayland # To use the wayland feature
              libglvnd
            ];
            LD_LIBRARY_PATH = lib.makeLibraryPath buildInputs;
          };
        }
    );
}
