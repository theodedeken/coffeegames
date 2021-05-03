import TreeOverview from "./TreeOverview.svelte";
import FileOverview from "./FileOverview.svelte";

export const routes = {
    '/tree': TreeOverview,
    // Wildcard parameter
    '/tree/*': TreeOverview,
    "/file/*": FileOverview,
}