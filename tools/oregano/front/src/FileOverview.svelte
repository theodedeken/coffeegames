<script lang="ts">
  import hljs from "highlight.js";
  import "highlight.js/styles/atom-one-dark.css";
  import Line from "./Line.svelte";
  import * as testdata from "./file.json";
  import FileHeader from "./FileHeader.svelte";

  export let content = testdata.content;
  export let name = testdata.name;

  const highlightedCode = hljs.highlight(content.join("\n"), {
    language: "java",
  }).value;
  const lines = highlightedCode.split("\n");
  let rest = "";
  let breadcrumbs = name.split("/").map((el) => {
    rest += "/" + el;
    return { directory: el, path: rest };
  });
  breadcrumbs.pop();
  let filename = name.split("/")[breadcrumbs.length];
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
