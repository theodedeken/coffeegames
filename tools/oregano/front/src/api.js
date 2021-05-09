import * as testData from "./data.json";

export function getTree(subpath) {
  let files = testData.default.map((el) => el.files).flat();

  if (subpath) {
    if (!subpath.endsWith("/")) {
      subpath += "/";
    }
    let selected = files.filter((f) => f.file_name.includes(subpath));
    return createTreeResponse(selected, subpath);
  } else {
    return createTreeResponse(files, "");
  }
}

export function getFile(path) {
  let file = testData.default
    .map((el) => el.files)
    .flat()
    .filter((el) => el.file_name === path)[0];
  return { ...file, overview: computeSummary(file.checks) };
}

function createTreeResponse(files, subpath) {
  let entries = createEntries(files, subpath);
  let checks = combineCheckSummaries(entries.map((entry) => entry.checks));
  return {
    subpath: subpath,
    checks: checks,
    entries: entries,
  };
}

function createEntries(files, subpath) {
  let entries = new Map();
  for (let file of files) {
    let first = file.file_name.replace(subpath, "").split("/")[0];
    if (!entries.has(first)) {
      entries.set(first, []);
    }
    entries.get(first).push(file);
  }
  return [...entries.entries()].map((pair) => ({
    name: pair[0],
    file: pair[0].includes("."),
    checks: combineCheckSummaries(
      pair[1].map((file) => computeSummary(file.checks))
    ),
  }));
}

function combineCheckSummaries(summaries) {
  let errors = 0;
  let warnings = 0;
  let infos = 0;
  for (let summary of summaries) {
    errors += summary.errors;
    warnings += summary.warnings;
    infos += summary.infos;
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
