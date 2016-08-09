/**
 * 调用后台批量删除方法
 */
function deleteMulti(basepath){
    $("#mainForm").attr("action", basepath + "DeleteMulti.action");
    $("#mainForm").submit();
}

