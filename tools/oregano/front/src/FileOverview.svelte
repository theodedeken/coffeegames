<script lang="ts">
  import hljs from "highlight.js";
  import "highlight.js/styles/atom-one-dark.css";
  import Line from "./Line.svelte";
  import FileHeader from "./FileHeader.svelte";
  import { getFile } from "./api.js";

  export let params;
  let file;
  let content;
  let name;
  let filename;
  let rest;
  let breadcrumbs;
  let lines;

  $: {
    file = getFile(params.wild);
    content = file.content;
    name = params.wild;

    const highlightedCode = hljs.highlight(content.join("\n"), {
      language: file.language,
    }).value;
    lines = highlightedCode.split("\n");
    rest = "";
    breadcrumbs = name.split("/").map((el) => {
      rest += "/" + el;
      return { directory: el, path: rest };
    });
    breadcrumbs.pop();
    filename = name.split("/")[breadcrumbs.length];
  }
</script>

<div>
  <FileHeader {filename} {breadcrumbs} />
  <div class="wrapper">
    <div class="child">
      {#each lines as line, i}
        <Line content={line} number={i} />
      {/each}
    </div>
    <div class="child">
      <pre
        class="hljs">
            <code >
                {@html lines.join("\n")}
            </code>
        </pre>
    </div>
  </div>
</div>

<style>
  .wrapper {
    display: flex;
  }
  .child {
    flex: 1;
  }
  div {
    text-align: left;
    padding: 1em;
    margin: 0 auto;
  }
</style>
