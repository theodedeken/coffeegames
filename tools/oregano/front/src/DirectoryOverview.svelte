<script lang="ts">
  import DirectoryEntry from "./DirectoryEntry.svelte";
  import DirectoryHeader from "./DirectoryHeader.svelte";
  import * as testdata from "./directory.json";

  export let subpath = testdata.subpath;
  export let entries = testdata.entries;
</script>

<div>
  <DirectoryHeader {subpath} />
  {#each entries as entry}
    <DirectoryEntry {...entry} />
  {/each}
</div>
