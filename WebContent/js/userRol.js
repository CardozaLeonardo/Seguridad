
$("#removeRoleBTN").click(function(){
    
    if($("#currentRoles").val())
    {
        idUser = $("#idUser").val();
        rolUser = $("#currentRoles").val();
        window.location.replace(`../SL_asignarRol?delete=${rolUser}&idUser=${idUser}`);

    }else{
        //textMSG = utf8_encode('Selección vacia')
        Swal.fire({
            type: 'warning',
            title: 'Selecci\u00F3n vacia',
            text: 'Seleccione un rol a eliminar'
        })
    }
});

$("#listaRoles").change(()=>{
    if($("#listaRoles").val())
    {
        if($(`.cr-${$("#listaRoles").val()}`).length){
            $("#submitRole").prop('disabled', true);
        }else{
            $("#submitRole").prop('disabled', false);
        }
    }
})