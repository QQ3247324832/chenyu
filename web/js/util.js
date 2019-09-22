var utilObj={
    "isNull":function (param) {
        if(param==""||param==null)
            return true;
        return false;
    },/*
    方法作用:根据地址对给定元素内容修改为给定内容
    参数1:元素"地址"
    参数2:1为修改后面全部元素,0为一个
    参数3:需要修改内容的元素名称
    参数4:元素内容文本*/
    "isNullParam":function (param,number,name,text) {
        var p = $(param).val();
        if(number==0){
            if(p==""||p==null){
                    $(param).next(name).text(text);
                return true;
            }
            $(param).next(name).text("");
            return false;
        }else{
            if(p==""||p==null){
                $(param).nextAll(name).text(text);
                return true;
            }
            $(param).nextAll(name).text("");
            return false;
        }

    },/*邮箱验证*/
    "emailLike":function (param) {
        var str = /\w+@\w+(\.\w+)+/;
        var email = $(param).val();
        /*邮箱验证*/
        return str.test(email);
    }
}