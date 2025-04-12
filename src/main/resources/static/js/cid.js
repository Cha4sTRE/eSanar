// Embedded Coding Tool settings object
// please note that only the property "apiServerUrl" is required
// the other properties are optional
const mySettings = {
    // The API located at the URL below should be used only for software
    // development and testing. ICD content at this location might not
    //  be up to date or complete. For production, use the API located at
    // id.who.int with proper OAUTH authentication
    language: "es",
    popudMode: true,
    apiServerUrl: "https://icd11restapi-developer-test.azurewebsites.net"
};

// example of an Embedded Coding Tool using the callback selectedEntityFunction
// for copying the code selected in an <input> element and clear the search results
const myCallbacks = {
    selectedEntityFunction: (selectedEntity) => {
        // paste the code into the <input>
        const input = document.getElementById('diagnosticoPrincipal');
        const diagnostico = input.value;
        // clear the searchbox and delete the search results
        ECT.Handler.clear("1")
        let title= selectedEntity.title;
        let code=selectedEntity.code;
        input.value = `${code}, ${title}`;
    }
};

// configure the ECT Handler with mySettings and myCallbacks
ECT.Handler.configure(mySettings, myCallbacks);
