<script lang="ts">
  import DirectoryEntry from "./DirectoryEntry.svelte";
  import DirectoryHeader from "./DirectoryHeader.svelte";
  import { getTree } from "./api.js";
  export let params;

  let tree;

  function sortEntries(first, second) {
    // If both elements are files or directories, sort by name
    if (first.file === second.file) {
      return first.name.toLowerCase() > second.name.toLowerCase();
    } else {
      return first.file > second.file;
    }
  }

  $: {
    if (params) {
      tree = getTree(params.wild);
    } else {
      tree = getTree("");
    }

    tree.entries.sort(sortEntries);
  }
</script>

<div>
  <DirectoryHeader subpath={tree.subpath} />
  {#each tree.entries as entry}
    <DirectoryEntry {...entry} subpath={tree.subpath} />
  {/each}
</div>
