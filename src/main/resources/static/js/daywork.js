var vue = new Vue({
    el:"#submit1",
    data:{
    },
    methods:{
        add(){
            if(document.getElementById("dayMiaoshu").value==""){
                alert("日志内容必填!");
                return false;
            }
        }
    }

})
