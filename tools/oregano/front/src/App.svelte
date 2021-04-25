<script lang="ts">
  import DirectoryOverview from "./DirectoryOverview.svelte";
  import File from "./File.svelte";
  import Router from 'svelte-spa-router';
  import {routes} from "./routes";

  export let subpath;
  export let entries;
</script>

<main>
  <Router {routes}/>
</main>

<style>
  main {
    padding: 1em;
    max-width: none;
    margin: 0 auto;
  }

  h1 {
    color: #ff3e00;
    text-transform: uppercase;
    font-size: 4em;
    font-weight: 100;
  }

  @media (min-width: 640px) {
    main {
      max-width: none;
    }
  }
</style>
