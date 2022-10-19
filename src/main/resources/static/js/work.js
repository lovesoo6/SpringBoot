new Vue({
    el:"#submit_xm",
    data:{
        cs:[]
    },
    methods:{

        sc(){
            //发送get请求
            let aa = this.cs;
            /*axios.get('/work/deleteCheckedMemorialsByGet?id=11111', {
                params: {
                    name: 12345
                }
            }).then(function (response) {
                    console.log(response);
                }).catch(function (error) {
                    console.log(error);
                });*/

            axios.post(
                "/work/deleteCheckedMemorialsByPost",{
                    params: {
                        cs:aa,
                    }
                }

            ).then(response=>{
                console.log(response.data);
            },function(){
                console.log('请求失败处理');
            });
        }
    }
})
