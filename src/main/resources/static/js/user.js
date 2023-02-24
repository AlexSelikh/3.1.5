const url = "http://localhost:8080/api/admin/api/user"
fetch(url)
    .then(response => response.json())
    .catch(error => console.log(error))

let userPageInfo = ' '
const showUserInfo = (user) => {
    const container = document.getElementById("tbody-user")
    userPageInfo +=
        `<tr>
            <td>${user.id}</td>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>${user.age}</td>
            <td>${user.username}</td>
            <td>${user.roles.map(role => role.name)}</td>
        </tr>`
    container.innerHTML = userPageInfo
}
fetch(url)
    .then(response => response.json())
    .then(data => showUserInfo(data))
    .catch(error => console.log(error))