new Vue({
    el:"#submit_xm",
    data:{
        cs:[]
    },
    methods:{

        sc(){
            //发送get请求
            let aa = this.cs;
            this.$http.post('/work/deleteCheckedMemorials/',{params:{ID:aa}},{emulateJSON:true}).then(function(res){
                document.write(res.body);
            },function(){
                console.log('请求失败处理');
            });
        }
    }
})
