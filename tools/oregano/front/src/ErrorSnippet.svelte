<script lang="ts">
  export let errorCount;
</script>

<div>
  <i class="ri-alert-fill" />
  {errorCount}
</div>

<style>
  div {
    margin-left: auto;
    background-color: var(--background-3);
    padding: 0.33em;
    border-radius: 0.25em;
  }
</style>
