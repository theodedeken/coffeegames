<script lang="ts">
  import DirectoryOverview from "./DirectoryOverview.svelte";
  import File from "./File.svelte";

  export let params = {};
  console.log(params);
</script>

{#if params.wild.includes(".")}
  <File />
{:else}
  <DirectoryOverview />
{/if}
