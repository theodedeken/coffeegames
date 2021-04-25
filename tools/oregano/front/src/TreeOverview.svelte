<script lang="ts">
  import DirectoryEntry from "./DirectoryEntry.svelte";
  import DirectoryHeader from "./DirectoryHeader.svelte";
  import * as testData from "./data.json";
  export let params;

  let subpath;
  let entries;
  let wild;
  $: {
    entries = testData["project"];
    if (params) {
      let wild = params.wild;
      let parts = wild.split("/");
      for (let el of parts) {
        entries = entries[el];
      }
      subpath = wild;
    } else {
      subpath = "";
    }
    entries = Object.keys(entries).map((el) => ({
      name: el,
      subpath: subpath,
      file: el.includes("."),
    }));
  }
</script>

<div>
  <DirectoryHeader {subpath} />
  {#each entries as entry}
    <DirectoryEntry {...entry} />
  {/each}
</div>
