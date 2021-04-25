<script lang="ts">
  import DirectoryOverview from "./DirectoryOverview.svelte";
  import File f./FileOverview.sveltevelte";
  import * as testData from "./data.json";

  export let params = {};
  let wild;
  let isFile = false;
  let entries;
  $: if (params.wild) {
    wild = params.wild;
    console.log(wild);
    entries = testData["project"];
    if (wild) {
      isFile = wild.includes(".");
      const parts = wild.split("/");
      for (let el of parts) {
        entries = entries[el];
      }
    } else {
      wild = "";
    }
    console.log(entries);
    entries = Object.keys(entries).map((el) => ({ name: el, subpath: wild }));
  }
</script>

{#if isFile}
  <File />
{:else}
  <DirectoryOverview subpath={wild} {entries} />
{/if}
