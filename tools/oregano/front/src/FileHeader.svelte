<script lang="ts">
    import BreadCrumb from "./BreadCrumb.svelte";
    
    export let breadcrumbs;
    export let filename;
    export let errorCount = 5;
</script>

<div>
  {#each breadcrumbs as breadcrumb}
    <BreadCrumb {...breadcrumb} />
  {/each}
  <h2>{filename}</h2>
  <div class="errors"><i class="ri-alert-fill" />{errorCount}</div>
</div>

<style>
    .errors {
        font-size: 5em;
        float: right;
        display: block;
    }
</style>
