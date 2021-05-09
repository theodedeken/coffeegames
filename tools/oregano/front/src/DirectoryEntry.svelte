<script lang="ts">
  import ErrorSnippet from "./ErrorSnippet.svelte";
  import ErrorHue from "./ErrorHue.svelte";

  export let name;
  export let file = false;
  export let subpath;
  export let checks;
  let entryLink;
  $: {
    entryLink = file ? "/file/" : "/tree/";
    if (subpath != "") {
      entryLink += subpath + name;
    } else {
      entryLink += name;
    }
  }
  /**
   * Function to move to the tree or file of the selected element
   */
  function moveToEntry() {
    location.href = "#" + entryLink;
  }
</script>

<div class="corners-border" on:click={moveToEntry}>
  <ErrorHue /> <i class={file ? "ri-file-fill" : "ri-folder-fill"} />
  <span>{name}</span> language <ErrorSnippet errorCount={checks.errors} />
</div>

<style>
  span {
    margin-left: 0.5em;
    margin-right: 0.5em;
  }
  div {
    padding: 0.66em;
    background-color: var(--background-1);
    color: white;
    display: flex;
    align-items: center;
    cursor: pointer;
    border-style: solid;
    border-width: 0.25em;
    border-color: var(--background-1);
  }

  i {
    font-size: 1.5em;
  }

  div:hover {
    border-color: white;
    border-image-source: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns=%22http:%2F%2Fwww.w3.org%2F2000%2Fsvg%22 viewBox=%220 0 80 80%22%3E%3Crect x=%224%22 y=%224%22 width=%2272%22 height=%2272%22 fill=%22transparent%22 stroke=%22%23aeffd8ff%22 stroke-width=%228%22 %2F%3E%3C%2Fsvg%3E");
    border-image-slice: 50%;
    border-image-width: 1.25em;
  }
</style>
