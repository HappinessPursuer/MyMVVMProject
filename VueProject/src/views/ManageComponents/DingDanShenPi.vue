<template>
    <div>
        <el-table
                id="OrderApprove"
                max-height="430px"
                :data="ordersToDisplay"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="idOrder"
                    label="订单ID"
                    width="150">
            </el-table-column>
            <el-table-column
                    width="150"
                    label="交割日期"
                    prop="ordertime">
            </el-table-column>
            <el-table-column
                    prop="purchasePrice"
                    label="进货价格"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="quantity"
                    label="进货数量"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="idCommodity"
                    label="商品ID"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="nameCommodity"
                    label="商品名称"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="idSupplier"
                    label="供应商ID"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="nameSupplier"
                    label="供货商名称"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="联系地址"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="postcode"
                    label="邮政编码"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="purchaserId"
                    label="采购员ID"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="purchaserName"
                    label="采购员名称"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="purchasetime"
                    label="采购时间"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="orderstate"
                    label="订单状态"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="manageOpinion"
                    label="经理意见"
                    width="150">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="审批"
                    width="200">
                <template slot-scope="scope">
                    <el-tooltip content="审批订单" placement="top" :open-delay="500">
                        <el-button @click="approveOne(scope.row)" type="info" size="small" icon="el-icon-goods"></el-button>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="审批订单" :visible.sync="orderToApproveDialogVisible" :center="true" >
            <el-form :model="orderToApprove" :rules="orderApproveRules" ref="approveOrderName">
                <el-form-item label="审批意见">
                    <el-input v-model="orderToApprove.manageOpinion"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" round @click="confirmApprove">确认修改</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "DingDanShenPi",
        data(){
            return{
                orderToApproveDialogVisible:false,
                ordersToDisplay:[
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
                ordersFull:[
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
                orderToApprove:{
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
                },//要审批的订单
                orderApproveRules:{
                    manageOpinion:[{
                        required:true,
                        message:'请输入审批意见',
                        trigger:'blur'
                    }]
                }
            }
        },
        methods:{
            refreshApproveTable(){
                this.$http.get('/orderDetails/findState/unapproved').then(res=>{
                    this.OrdersFull=res.data;
                    console.log(res)
                });
                this.ordersToDisplay=this.OrdersFull;
            },
            approveOne(row){
                this.orderToApprove=row;
                this.orderToApproveDialogVisible=true;
            },
            confirmApprove(){
                this.$refs.approveOrderName.validate(async valid=>{
                    if (valid){
                        this.orderToApprove.orderstate='approved';
                        this.orderToApprove.manageId=this.$store.state.userCurrent.idUser;
                        this.orderToApprove.manageName=this.$store.state.userCurrent.username;
                        const {data:res}=await this.$http.post('/orderDetails/update',this.orderToApprove);
                        console.log(res);
                        if(res){
                            this.$message.success('审批成功');//设置成功的消息
                            this.refreshApproveTable();
                            this.orderToApproveDialogVisible=false;
                        }else{
                            this.$message.error("审批订单失败");
                            return false;
                        }
                    } else {
                        this.$message({
                            message: '请输入正确的格式',
                            type: 'warning'
                        });
                        return false;
                    }
                });
            }
        },
        created() {
            this.$http.get('/orderDetails/findState/unapproved').then(res=>{
                this.OrdersFull=res.data;
                console.log(res)
            });
            this.ordersToDisplay=this.OrdersFull;
        }
    }
</script>

<style scoped>

</style>