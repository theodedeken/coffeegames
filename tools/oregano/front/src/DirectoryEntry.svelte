<script lang="ts">
  import { link } from "svelte-spa-router";

  export let name;
  export let file = false;
  export let subpath;
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
    {name} language error_overview
  </div>
</a>

<style>
  div {
    padding: 0.5em;
    background-color: black;
    color: white;
  }
</style>
