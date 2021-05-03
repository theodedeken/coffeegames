import svelte from "rollup-plugin-svelte";
import commonjs from "@rollup/plugin-commonjs";
import { nodeResolve } from "@rollup/plugin-node-resolve";
import sveltePreprocess from "svelte-preprocess";
import css from "rollup-plugin-css-only";
import json from "@rollup/plugin-json";

class ResolveInternal {
  resolveId(source) {
    console.log(source);
    const workspace = process.env.BAZEL_WORKSPACE;

    if (
      (workspace && source.startsWith("./") && source.includes("svelte")) ||
      source == "./data.json"
    ) {
      const importPath = source.substring(2);
      const compilation_mode = process.env.COMPILATION_MODE;
      let k8Folder = "";
      if (compilation_mode === "fastbuild") {
        k8Folder = "k8-fastbuild"; // might be different on your system, e.g. "k8-opt"
      } else if (compilation_mode === "opt") {
        k8Folder = "k8-opt";
      }

      return {
        id: `bazel-out/${k8Folder}/bin/tools/oregano/front/src/${importPath}${
          source.includes("svelte") ? ".js" : ""
        }`,
        external: false,
      };
    } else if (source.startsWith("./")) {
      return {
        id: process.cwd() + "/tools/oregano/front/src" + source.substring(1),
        external: false,
      };
    }
    return null; // default resolution
  }
}

module.exports = {
  output: {
    name: "app",
  },
  plugins: [
    svelte({
      preprocess: sveltePreprocess({ sourceMap: true }),
      compilerOptions: {
        // enable run-time checks when not in production
        dev: false,
      },
    }),

    // If you have external dependencies installed from
    // npm, you'll most likely need these plugins. In
    // some cases you'll need additional configuration -
    // consult the documentation for details:
    // https://github.com/rollup/plugins/tree/master/packages/commonjs
    nodeResolve({
      browser: true,
      dedupe: ["svelte"],
      moduleDirectories: ["external/npm/node_modules"],
    }),
    commonjs(),
    new ResolveInternal(),

    // we'll extract any component CSS out into
    // a separate file - better for performance
    css({ output: "bundle.css" }),

    // JSON plugin
    json(),
  ],
};
