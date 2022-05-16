<template>
    <div>
        <el-header id="OrderSystemHeader">
            <div id="OrderHeaderDiv">
                <el-checkbox class="orderHeaderCheckBox" v-model="orderHeaderCheckbox1" @change="orderHeaderCheckboxChanged">未审批</el-checkbox>
                <el-checkbox class="orderHeaderCheckBox" v-model="orderHeaderCheckbox2" @change="orderHeaderCheckboxChanged">历史记录</el-checkbox>
            </div>
        </el-header>
        <el-table
                max-height="430px"
                id="OrderBaseTable"
                :data="detailOrdersToDisplay"
                style="width: 100%">
            <el-table-column
                    label="订单ID"
                    prop="idOrder">
            </el-table-column>
            <el-table-column
                    label="商品名称"
                    prop="nameCommodity">
            </el-table-column>
            <el-table-column
                    label="采购数量"
                    prop="quantity">
            </el-table-column>
            <el-table-column
                    label="供货商ID"
                    prop="idSupplier">
            </el-table-column>
            <el-table-column
                    label="详情或编辑"
                    fixed="right">
                <template slot-scope="scope">
                    <el-tooltip content="编辑：仅限未审批的订单" placement="top" :open-delay="500">
                        <el-button @click="editOrder(scope.row)" type="info" size="small" icon="el-icon-s-tools"></el-button>
                    </el-tooltip>
                    <el-tooltip content="删除：仅可撤销未审批的订单" placement="top" :open-delay="500">
                        <el-button @click="deleteOrder(scope.row)" type="info" size="small" icon="el-icon-delete"></el-button>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="编辑订单" :visible.sync="editOrdersDialogVisible" :center="true" >
            <el-form ref="editOrderRef" :rules="editOrderRules" :model="oneOrderToEdit">

                <el-form-item prop="quantity" label="采购数量" >
                    <el-input v-model="oneOrderToEdit.quantity"></el-input>
                </el-form-item>
                <el-form-item prop="idSupplier" label="供货商ID">
                    <el-input v-model="oneOrderToEdit.idSupplier"></el-input>
                </el-form-item>
                <el-form-item prop="address" label="供应商地址">
                    <el-input v-model="oneOrderToEdit.address"></el-input>
                </el-form-item>
                <el-form-item prop="postcode" label="供货商邮箱">
                    <el-input v-model="oneOrderToEdit.postcode"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" round @click="confirmEditOrder">确认订单</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "DingDanXiTong",
        data(){
            return{
                orderHeaderCheckbox1:false,
                orderHeaderCheckbox2:false,
                detailOrdersToDisplay:[
                    {
                        idOrder:'',
                        ordertime:'',
                        purchasePrice:'',
                        quantity:'',
                        idCommodity:'',
                        nameCommodity:'',
                        idSupplier:'',
                        nameSupplier: '',
                        address:'',
                        postcode: '',
                        purchaserId:'',
                        purchaserName:'',
                        purchasetime:'',
                        orderstate:'',
                        manageId:'',
                        manageName:'',
                        manageTime:'',
                        manageOpinion:''
                    }
                ],//用于展示的数组
                detailOrdersFull:[
                    {
                        idOrder:'',
                        ordertime:'',
                        purchasePrice:'',
                        quantity:'',
                        idCommodity:'',
                        nameCommodity:'',
                        idSupplier:'',
                        nameSupplier: '',
                        address:'',
                        postcode: '',
                        purchaserId:'',
                        purchaserName:'',
                        purchasetime:'',
                        orderstate:'',
                        manageId:'',
                        manageName:'',
                        manageTime:'',
                        manageOpinion:''
                    }
                ],//后端请求过来的完全数组
                editOrdersDialogVisible:false,//隐藏编辑订单表对话框
                editOrderRules:{
                    quantity:[{
                        required:true,
                        message:'请输入商品数量',
                        trigger:'blur'
                    }],
                    idSupplier:[{
                        required:true,
                        message:'请输入供货商ID',
                        trigger:'blur'
                    }],
                    address:[{
                        required:true,
                        message:'请输入供货商地址',
                        trigger:'blur'
                    }],
                    postcode:[{
                        required:true,
                        message:'请输入供货商邮箱',
                        trigger:'blur'
                    }],
                },//采购员编辑订单的规则
                oneOrderToEdit:{
                    idOrder:'',
                    ordertime:'',
                    purchasePrice:'',
                    quantity:'',
                    idCommodity:'',
                    nameCommodity:'',
                    idSupplier:'',
                    nameSupplier: '',
                    address:'',
                    postcode: '',
                    purchaserId:'',
                    purchaserName:'',
                    purchasetime:'',
                    orderstate:'',
                    manageId:'',
                    manageName:'',
                    manageTime:'',
                    manageOpinion:''
                }
            }
        },
        methods:{
            refeshFullOrder(){
                this.$http.get('/orderDetails/list').then(res=>{
                    this.detailOrdersFull=res.data;
                });
            },
            orderHeaderCheckboxChanged(){
                if(!this.orderHeaderCheckbox1&&!this.orderHeaderCheckbox2){//如果选框1、2都没有被选中,展示所有内容
                    this.detailOrdersToDisplay=[];
                    return;
                }
                if(this.orderHeaderCheckbox1&&this.orderHeaderCheckbox2){//如果选框1、2都被选中，展示所有内容
                    this.detailOrdersToDisplay=this.detailOrdersFull;
                    return;
                }
                if(!this.orderHeaderCheckbox1&&this.orderHeaderCheckbox2){//选框1没选中，选框2选中，只选已经审批的内容展示
                    this.$http.get('/orderDetails/findState/approved').then(res=>{
                        this.detailOrdersToDisplay=res.data;
                        console.log(res)
                    });
                    return;
                }
                if(this.orderHeaderCheckbox1&&!this.orderHeaderCheckbox2){//如果选框1被选中但是选框2没有选中，只展示未审批的内容
                    this.$http.get('/orderDetails/findState/unapproved').then(res=>{
                        this.detailOrdersToDisplay=res.data;
                        console.log(res)
                    });
                    return;
                }
            },
            deleteOrder(row){
                let _this=this;
                this.$confirm('将永久删除订单:'+row.idOrder,'撤销订单',{
                    confirmButtonText:'确定',
                    cancelButtonText:'取消',
                    type:'warning'
                }).then(()=>{
                    this.$http.delete('http://localhost:8181/orderDetails/delete/'+row.idOrder).then(function (response) {
                        if(response.data){
                            _this.$alert('订单'+row.idOrder+'删除成功','删除数据',{
                                confirmButtonText:'确定',
                            })
                            _this.orderHeaderCheckbox1=false;
                            _this.orderHeaderCheckbox2=false;
                            _this.detailOrdersToDisplay=[];
                            _this.refeshFullOrder();
                        }
                    })
                });
            },//点击详情触发的事件
            editOrder(row){
                if(row.orderstate==='approved'){
                    this.$message.error("订单已审批，无权访问！");
                    return false;
                }else {
                    this.oneOrderToEdit=row;
                    this.editOrdersDialogVisible=true;
                }
            },//表格中的编辑订单
            confirmEditOrder(){
                this.$refs.editOrderRef.validate(async valid=>{
                    if (valid){
                        const {data:res}=await this.$http.post('/orderDetails/update',this.oneOrderToEdit);
                        console.log(res);
                        if(res){
                            this.$message.success('修改成功');//设置成功的消息
                            this.orderHeaderCheckbox1=false;
                            this.orderHeaderCheckbox2=false;
                            this.detailOrdersToDisplay=[];
                            this.editOrdersDialogVisible=false;
                        }else{
                            this.$message({
                                message: '修改失败',
                                type: 'error'
                            });
                            return false;
                        }
                    } else {
                        this.$message({
                            message: '请输入正确的修改格式',
                            type: 'warning'
                        });
                        return false;
                    }
                });
            }//确认订单修改
        },
        created() {
            this.$http.get('/orderDetails/list').then(res=>{
                this.detailOrdersFull=res.data;
            });
            this.detailOrdersToDisplay=this.detailOrdersFull;
        }
    }
</script>

<style scoped>
    #OrderSystemHeader{
        width: 104%;
        margin-left: -20px;
        margin-top: -20px;
        background: #C0C4CC;
        box-shadow: 0 0 5px ;
    }
    #OrderSystemMenu{
        display: flow;
    }
    .OrderMenuItem{
        width: 200px;
    }
    #OrderBaseTable{
        margin-top: 10px;
    }
    .demo-table-expand {
        font-size: 0;
    }
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
    .orderHeaderCheckBox{
        margin-left: 35px;
        color: white;
        margin-top: 20px;
    }
    #OrderHeaderDiv{
        margin-left: 50px;
        width: 300px;
        height: 60px;
        background:#909399;
        box-shadow: 0 0 5px #303133;
    }
</style>