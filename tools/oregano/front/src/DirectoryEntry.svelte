<script lang="ts">
  import { link } from "svelte-spa-router";
  import ErrorSnippet from "./ErrorSnippet.svelte";

  export let name;
  export let file = false;
  export let subpath;
  export let checks;
  let entryLink;
  $: {
    entryLink = file ? "/file/" : "/tree/";
    if (subpath != "") {
      entryLink += subpath + "/" + name;
    } else {
      entryLink += name;
    }
  }
</script>

<a use:link={entryLink}>
  <div>
    block <i class={file ? "ri-file-fill" : "ri-folder-fill"} />
    {name} language <ErrorSnippet errorCount={checks.errors} />
  </div>
</a>

<style>
  div {
    padding: 0.5em;
    background-color: black;
    color: white;
    display: flex;
  }
</style>
