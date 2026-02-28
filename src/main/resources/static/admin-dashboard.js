function openForm(){
    document.getElementById("studentModal").style.display = "flex";
}

function closeForm(){
    document.getElementById("studentModal").style.display = "none";
}

async function createStudent(){

    const student = {
        username:     document.getElementById("username").value,
        fullName:     document.getElementById("fullName").value,
        rollNumber:   document.getElementById("rollNumber").value,
        department:   document.getElementById("department").value,
        semester:     parseInt(document.getElementById("semester").value),
        cgpa:         parseFloat(document.getElementById("cgpa").value),
        attendance:   parseFloat(document.getElementById("attendance").value),
        tempPassword: document.getElementById("tempPassword").value  // ← ADD THIS
    };

    try{

        const response = await fetch("/api/admin/create-student",{

            method:"POST",

            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer " + localStorage.getItem("jwt") // ✅ IMPORTANT
            },

            body: JSON.stringify(student)

        });

        const result = await response.text();

        if(!response.ok){
            alert(result);
            return;
        }

        alert(result);

        closeForm();

    }
    catch(error){
        alert("Server error");
    }
}