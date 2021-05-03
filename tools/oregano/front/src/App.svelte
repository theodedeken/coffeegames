<script lang="ts">
  import Router from "svelte-spa-router";
  import { routes } from "./routes.js";
</script>

<svelte:head>
  <link
    href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css"
    rel="stylesheet"
  />
  <link rel="preconnect" href="https://fonts.gstatic.com" />
  <link
    href="https://fonts.googleapis.com/css2?family=Anonymous+Pro:ital,wght@0,400;0,700;1,400;1,700&family=Overpass+Mono&display=swap"
    rel="stylesheet"
  />
</svelte:head>

<main>
  <Router {routes} />
</main>

<style>
  main {
    padding: 1em;
    max-width: none;
    margin: 0 auto;
    font-family: "Overpass Mono", monospace;
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
