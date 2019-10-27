
$("#removeRoleBTN").click(function(){
    
    if($("#currentRoles").val())
    {
        idUser = $("#idUser").val();
        rolUser = $("#currentRoles").val();
        window.location.replace(`../SL_asignarRol?delete=${rolUser}&idUser=${idUser}`);

    }else{
        //textMSG = utf8_encode('Selección vacia')
        
        warningAlert("Selecci\u00F3n vacia", "Seleccione un rol a eliminar");
    }
});


$("#removeOpcBTN").click(function(){
    
    if($("#currentOpc").val())
    {
        idRol = $("#idRol").val();
        opcion = $("#currentOpc").val();
        window.location.replace(`../SL_asignarOpciones?delete=${opcion}&idRol=${idRol}`);

    }else{
        //textMSG = utf8_encode('Selección vacia')
        
        warningAlert("Selecci\u00F3n vacia", "Seleccione una opci\u00F3n a eliminar");
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