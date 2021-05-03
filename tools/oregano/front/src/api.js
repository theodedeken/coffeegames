import * as testData from "./data.json";

export function getTree(subpath) {
  let current = testData["tree"];
  if (subpath) {
    let parts = subpath.split("/");
    for (let part of parts) {
      current = current[part];
    }
  }
  return treeResponse(current, subpath);
}

function treeResponse(tree, subpath) {
  return {
    subpath: subpath,
    checks: computeCheckSummary(tree),
    entries: Object.keys(tree).map((el) => ({
      name: el,
      file: el.includes("."),
      checks: computeCheckSummary(tree[el]),
    })),
  };
}

function computeCheckSummary(tree) {
  let errors = 0;
  let warnings = 0;
  let infos = 0;
  if ("checks" in tree) {
    let overview = computeSummary(tree.checks);
    errors += overview.errors;
    warnings += overview.warnings;
    infos += overview.infos;
  } else {
    let children = Object.keys(tree);
    for (let child of children) {
      let overview = computeCheckSummary(tree[child]);
      errors += overview.errors;
      warnings += overview.warnings;
      infos += overview.infos;
    }
  }
  return {
    errors: errors,
    warnings: warnings,
    infos: infos,
  };
}

function computeSummary(checks) {
  let errors = 0;
  let warnings = 0;
  let infos = 0;
  for (let check of checks) {
    if ((check.severity = "error")) {
      errors++;
    } else if (check.severity == "warning") {
      warnings++;
    } else {
      infos++;
    }
  }
  return {
    errors: errors,
    warnings: warnings,
    infos: infos,
  };
}
