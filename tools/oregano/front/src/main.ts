import App from './App.svelte';
import * as testdata from "./directory.json";

const app = new App({
	target: document.body,
	props: testdata
});

export default app;