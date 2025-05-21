function toggleUserStatus(checkbox){
    let id= checkbox.getAttribute("data-id")
    let enabled= checkbox.checked;
    let csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
    let csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");

    fetch("isEnable",{
        method:"POST",
        headers:{
            "Content-Type": "application/json",
            [csrfHeader]: csrfToken
        },
        body: "id="+id+"&enabled="+enabled,
    })
        .then(response => response.text())
        .then(data=>console.log("estado actualizado "+data))
        .catch(error => console.log(error))
}