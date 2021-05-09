def oregano(name, deps, autoupdate = True):
    oregano_runner(
        name = "%s.runner",
        autoupdate = autoupdate,
    )
    if autoupdate:
        oregano_autoupdate(
            name = "%s.autoupdate",
        )
    oregano_run(
        name = "%s.run",
        deps = deps,
    )

    sh_binary(
        name = name,
        srcs = ["%s.runner"],
    )
