<template>
    <div>
<!--两部分：头和一个表-->
        <el-container >
            <el-header id="commodityHeader">
<!--搜索框,其中clearable是清空搜索框的按钮-->
                <el-tooltip content="回车开始搜索" placement="top" :open-delay="delayTime">
                    <el-input id="commoditySearch"
                              v-model="searchText"
                              prefix-icon="el-icon-search"
                              type="text"
                              placeholder="输入搜索内容"
                              @keyup.enter.native="searchCommodity"
                              clearable
                              @input="searchTextChange"
                              style="width: 200px;margin-top: 10px">
                    </el-input>
                </el-tooltip>
<!--自动生成订单按钮-->
                <el-tooltip content="给所有被选择的商品生成订单" placement="top" :open-delay="500">
                    <el-button id="commodityToOrder" type="warning" plain @click="generateOrders" style="margin-left: 700px" icon="el-icon-s-order">生成订单</el-button>
                </el-tooltip>
<!--添加商品按钮-->
                <el-button id="commodityAppend" type="warning" plain @click="appendCommodity" icon="el-icon-circle-plus">添加商品</el-button>
            </el-header>
            <el-table ref="multipleTable"
                      id="commodityTable"
                      max-height="430px"
                      :data="items"
                      tooltip-effect="dark"
                      style="width: 100%"
                      @selection-change="handleSelectionChange">
                <el-table-column fixed type="selection" width="55"></el-table-column>
                <el-table-column fixed prop="idCommodity" label="商品ID" width="100"></el-table-column>
                <el-table-column prop="nameCommodity" label="商品名称" width="150"></el-table-column>
                <el-table-column prop="quantity"
                                 label="库存数量"
                                 width="150"
                                 :filters="[{text:'小于'+this.minQuantity,value:this.minQuantity}]"
                                 :filter-method="filterQuantity"
                ></el-table-column>
                <el-table-column prop="specifications" label="商品规格" width="150"></el-table-column>
                <el-table-column prop="purchasePrice" label="进货价格" width="150"></el-table-column>
                <el-table-column prop="salePrice" label="在售价格" width="150"></el-table-column>
                <el-table-column prop="vipPrice" label="会员价格" width="150"></el-table-column>
                <el-table-column
                        fixed="right"
                        label="编辑或删除"
                        width="200">
                    <template slot-scope="scope">
                        <el-tooltip content="订货" placement="top" :open-delay="500">
                            <el-button @click="orderOne(scope.row)" type="info" size="small" icon="el-icon-goods"></el-button>
                        </el-tooltip>
                        <el-tooltip content="修改" placement="top" :open-delay="500">
                            <el-button @click="editOne(scope.row)" type="info" size="small" icon="el-icon-edit"></el-button>
                        </el-tooltip>
                        <el-tooltip content="删除" placement="top">
                            <el-button @click="deleteOne(scope.row)" type="info" size="small" icon="el-icon-delete"></el-button>
                        </el-tooltip>
                    </template>
                </el-table-column>
            </el-table>
        </el-container>

<!--添加商品的对话框-->
        <el-dialog title="添加商品" :visible.sync="addCommodityVisible" :center="true">
            <el-form :model="commodityToAppend" :rules="appendCommodityRules" ref="addCommodityName">
                <el-form-item label="商品ID">
                    <el-input v-model="commodityToAppend.idCommodity" :readonly="true" placeholder="自动生成，无需添加"></el-input>
                </el-form-item>
                <el-form-item prop="nameCommodity" label="商品名称">
                    <el-input v-model="commodityToAppend.nameCommodity"></el-input>
                </el-form-item>
                <el-form-item label="商品规格">
                    <el-input v-model="commodityToAppend.specifications"></el-input>
                </el-form-item>
                <el-form-item prop="quantity" label="库存数量">
                    <el-input v-model="commodityToAppend.quantity"></el-input>
                </el-form-item>
                <el-form-item prop="purchasePrice" label="进货价格">
                    <el-input v-model="commodityToAppend.purchasePrice"></el-input>
                </el-form-item>
                <el-form-item prop="salePrice" label="在售价格">
                    <el-input v-model="commodityToAppend.salePrice"></el-input>
                </el-form-item>
                <el-form-item prop="vipPrice" label="会员价格">
                    <el-input v-model="commodityToAppend.vipPrice"></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" round @click="confirmAppend">确认添加</el-button>
                <el-button type="info" round @click="resetAppend">重置</el-button>
            </div>
        </el-dialog>
<!--修改商品的对话框-->
        <el-dialog title="修改商品" :visible.sync="editCommodityVisible" :center="true" >
            <el-form :model="commodityToEdit" :rules="editCommodityRules" ref="editCommodityName">
                <el-form-item label="商品ID">
                    <el-input v-model="commodityToEdit.idCommodity" :readonly="true"></el-input>
                </el-form-item>
                <el-form-item prop="nameCommodity" label="商品名称">
                    <el-input v-model="commodityToEdit.nameCommodity"></el-input>
                </el-form-item>
                <el-form-item label="商品规格">
                    <el-input v-model="commodityToEdit.specifications"></el-input>
                </el-form-item>
                <el-form-item prop="quantity" label="库存数量" >
                    <el-input v-model="commodityToEdit.quantity" :readonly="true"></el-input>
                </el-form-item>
                <el-form-item prop="purchasePrice" label="进货价格">
                    <el-input v-model="commodityToEdit.purchasePrice"></el-input>
                </el-form-item>
                <el-form-item prop="salePrice" label="在售价格">
                    <el-input v-model="commodityToEdit.salePrice"></el-input>
                </el-form-item>
                <el-form-item prop="vipPrice" label="会员价格">
                    <el-input v-model="commodityToEdit.vipPrice"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" round @click="confirmEdit">确认修改</el-button>
            </div>
        </el-dialog>
<!--生成订单的对话框-->
        <el-dialog title="生成订单" :visible.sync="oneCommodityOrderDialogVisible" :center="true" >
            <el-form ref="orderCommodityName" :rules="oneCommodityOrderRules" :model="oneCommodityOrder">
                <el-form-item prop="ordertime" label="交割时间">
                    <el-input v-model="oneCommodityOrder.ordertime"></el-input>
                </el-form-item>
                <el-form-item label="采购价格" prop="purchasePrice">
                    <el-input v-model="oneCommodityOrder.purchasePrice"></el-input>
                </el-form-item>
                <el-form-item prop="quantity" label="采购数量" >
                    <el-input v-model="oneCommodityOrder.quantity"></el-input>
                </el-form-item>
                <el-form-item label="商品名称">
                    <el-input v-model="oneCommodityOrder.nameCommodity" :readonly="true"></el-input>
                </el-form-item>
                <el-form-item prop="idSupplier" label="供应商ID">
                    <el-input v-model="oneCommodityOrder.idSupplier"></el-input>
                </el-form-item>
                <el-form-item label="采购员ID">
                    <el-input v-model="oneCommodityOrder.purchaserId" :readonly="true"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" round @click="oneCommodityOrderCommit">确认订单</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'ShangPinGuanLi',
        data(){
            return{
                oneCommodityOrderDialogVisible:false,//隐藏单个订单生成表
                oneCommodityOrder:{
                    idOrder:'',//
                    ordertime:'',
                    purchasePrice:'',
                    quantity:'',
                    idCommodity:'',//
                    nameCommodity:'',//
                    idSupplier:'',
                    nameSupplier: '',
                    address:'',
                    postcode: '',
                    purchaserId:this.$store.state.userCurrent.idUser,//
                    purchaserName:this.$store.state.userCurrent.username,//
                    purchasetime:'',//
                    orderstate:'unapproved',//
                    manageId:'',//
                    manageName:'',//
                    manageTime:'',//
                    manageOpinion:''//
                },
                oneCommodityOrderRules:{
                    purchasePrice:[{
                        required:true,
                        message:'请输入商品价格',
                        trigger:'blur'
                    }],
                    quantity:[{
                        required:true,
                        message:'请输入采购数量',
                        trigger:'blur'
                    }],
                    idSupplier:[{
                        required:true,
                        message:'请输入供应商ID',
                        trigger:'blur'
                    }],
                },

                delayTime:500,
                items: [
                    {
                        idCommodity:'',
                        nameCommodity:'',
                        specifications:'',
                        purchasePrice:'',
                        salePrice:'',
                        vipPrice:'',
                        quantity:''
                    }
                ],//商品表不涉及安全性问题，所以在前端完成
                addCommodityVisible:false,//隐藏添加商品的Dialog
                editCommodityVisible:false,//隐藏编辑商品的Dialog
                commodityToAppend:{
                    idCommodity:'',
                    nameCommodity:'',
                    specifications:'',
                    purchasePrice:'',
                    salePrice:'',
                    vipPrice:'',
                    quantity:''
                },//用于保存添加的商品的数据（添加商品的缓存）
                commodityToEdit:{
                    idCommodity:'',
                    nameCommodity:'',
                    specifications:'',
                    purchasePrice:'',
                    salePrice:'',
                    vipPrice:'',
                    quantity:''
                },//被编辑的商品的当前值，用于提交
                commodityBeforeEdit:{
                    idCommodity:'',
                    nameCommodity:'',
                    specifications:'',
                    purchasePrice:'',
                    salePrice:'',
                    vipPrice:'',
                    quantity:''
                },//存储被编辑的商品的初始值，用于重置
                appendCommodityRules:{
                    nameCommodity:[{
                        required:true,
                        message:'请输入商品名称',
                        trigger:'blur'
                    }],
                    purchasePrice:[{
                        required:true,
                        message:'请输入进货价格',
                        trigger:'blur'
                    }],
                    salePrice:[{
                        required:true,
                        message:'请输入在售价格',
                        trigger:'blur'
                    }],
                    VipPrice:[{
                        required:true,
                        message:'请输入会员价格',
                        trigger:'blur'
                    }],
                    quantity:[{
                        required:true,
                        message:'请输入在库数量',
                        trigger:'blur'
                    }]
                },//添加商品规则
                editCommodityRules:{
                    nameCommodity:[{
                        required:true,
                        message:'请输入商品名称',
                        trigger:'blur'
                    }],
                    purchasePrice:[{
                        required:true,
                        message:'请输入进货价格',
                        trigger:'blur'
                    }],
                    salePrice:[{
                        required:true,
                        message:'请输入在售价格',
                        trigger:'blur'
                    }],
                    VipPrice:[{
                        required:true,
                        message:'请输入会员价格',
                        trigger:'blur'
                    }]
                },//编辑商品规则
                fullitems:[
                    {
                        idCommodity:'',
                        nameCommodity:'',
                        specifications:'',
                        purchasePrice:'',
                        salePrice:'',
                        vipPrice:'',
                        quantity:'',
                    }
                ],//我们搜索到的内容都先存到这个完全数组中
                itemsSearched:[],//保存被搜索到的数组
                searchText:'',//搜索框中的文字
                minQuantity:30,//过滤器中，最小过滤数量
                commoditiesSelected:[{
                    idCommodity:'',
                    nameCommodity:'',
                    specifications:'',
                    purchasePrice:'',
                    salePrice:'',
                    vipPrice:'',
                    quantity:''
                }],//保存商品多选表中被选中的商品
                commoditiesSelectedNum:0,//多选表中被选中的行数，等于0就无法执行自动生成订单

                orderBaseRules:{
                    idOrder:'',
                    idCommodity:'',
                    idSupplier: '',
                    totalCost:'',
                    orderState:''
                },//基本表格式
                orderBaseItem:{
                    idOrder:'',
                    idCommodity:'',
                    idSupplier: '',
                    totalCost:'',
                    orderState:''
                },//要添加的一条基本表
                orderDetailsRules:{
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
                },//详情表格式
                orderDetailsItem:{
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
                },//要添加的一条详情表
                supplier:[
                    {
                        address:'',
                        contact:'',
                        creditBank:'',
                        creditCard:'',
                        idCommodity:'',
                        idSupplier:'',
                        nameSupplier:'',
                        phone:'',
                        postcode:'',
                        purchasePrice:'',
                        telephone:''
                    }
                ],//查到的供应商
                orderBaseListToAppend:[],//要添加的订单基本表
                orderDetailsListToAppend:[],//要添加的详细表
            }
        },
        created() {
            let _this=this;
            this.$http.get('commodity/list').then(function (response) {
                _this.items=response.data;
                _this.fullitems=response.data;
            });
            this.$http.get('/supplier/list').then(res=>{
                this.supplier=res.data;
            });
        },
        methods:{
            oneCommodityOrderCommit(){
                this.$refs.orderCommodityName.validate(async valid=>{
                    if (valid){
                        const {data:res}=await this.$http.post('/orderDetails/append',this.oneCommodityOrder);
                        console.log(res);
                        if(res){
                            this.$message.success('生成订单成功');//设置成功的消息
                            this.oneCommodityOrderDialogVisible=false;//关闭添加对话框
                        }else{
                            this.$message.error("生成订单失败");
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
            },//提交一个商品详细订单
            orderOne(row){
                this.oneCommodityOrderDialogVisible=true;
                this.oneCommodityOrder.idCommodity=row.idCommodity;
                this.oneCommodityOrder.nameCommodity=row.nameCommodity;
            },//table中产生单个订单，显示添加订单视图

            generateOrders(){
                if(this.commoditiesSelectedNum===0){
                    this.$message.warning("请选择要生成订单的商品！")
                    return;
                }
                let nameList='';
                for (let i=0;i<this.commoditiesSelected.length;i++){
                    nameList=nameList+this.commoditiesSelected[i].nameCommodity+' ';//添加显示信息
                    this.orderBaseListToAppend.push({
                        idOrder:'',
                        idCommodity:'',
                        idSupplier: '',
                        totalCost:'',
                        orderState:''
                    });
                }//添加提示信息，创建等长数组
                for (let i=0;i<this.commoditiesSelected.length;i++){
                    this.orderBaseListToAppend[i].idCommodity=this.commoditiesSelected[i].idCommodity;//获取商品Id
                    for(let j=0;j<this.supplier.length;j++){
                        if(this.commoditiesSelected[i].idCommodity===this.supplier[j].idCommodity){
                            this.orderBaseListToAppend[i].idSupplier=this.supplier[j].idSupplier;//获取供应商Id
                        }
                    }
                    this.orderBaseListToAppend[i].orderState='unapproved';
                }//自动填写订单基本表
                for(let i=0;i<this.orderBaseListToAppend.length;i++){
                    console.log(this.orderBaseListToAppend[i]);
                }//控制台显示订单基本表
                this.$confirm('将自动生成以下商品订单：'+nameList, '生成订单', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$http.post('/orderBase/append',this.orderBaseListToAppend).then(res=>{
                        console.log(res.data);
                        if(res.data){//如果接收到res的data为true时，表示成功
                            this.$message.success('订单基本表生成成功，详情请查看订单系统');
                        }else {
                            this.$message.error('订单基本表添加出错');
                        }
                    });

                    // this.$http.post('')
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '订单取消添加'
                    });
                });
                this.orderBaseListToAppend=[];
            },//批量生成订单的方法
            filterQuantity(value,row,column){
                const property = column['property'];
                return row[property] <= value;
            },//商品库存数量的过滤器方法
            handleSelectionChange(val){
                this.commoditiesSelected=val;
                this.commoditiesSelectedNum=val.length;
            },//每次商品表多选框发生改变的时候，都会调用该方法，我把被选中的商品放到了一个数组中commoditiesSelected
            searchTextChange(){
                if(this.searchText.length===0){
                    this.items=this.fullitems;
                    return;
                }//如果输入框是空的，就刷新表格
                for(let i=0;i<this.fullitems.length;i++){
                    if (this.fullitems[i].nameCommodity.indexOf(this.searchText)>=0){
                        console.log(this.fullitems[i]);
                        this.itemsSearched.push(this.fullitems[i]);
                    }
                }//把所有名字遍历一遍，查找到名字中有当前字符的对象，就把它添加到被搜索到的数组中；三个重要变量：传入值、查找域、显示域
                this.items=this.itemsSearched;//显示在表格中
                this.itemsSearched=[];//清空缓存
            },//每次搜索框有变化时都要执行这个方法
            searchCommodity(){
                if(this.searchText.length===0){
                    this.$message.warning("请输入查询内容！")
                }
            },//搜索框回车的方法
            appendCommodity(){
                this.addCommodityVisible=true;
            },//打开添加商品对话框
            confirmAppend(){
                this.$refs.addCommodityName.validate(async valid=>{
                    if (valid){
                        const {data:res}=await this.$http.post('/commodity/append',this.commodityToAppend);
                        console.log(res);
                        if(res){
                            this.$message.success('添加成功');//设置添加成功的消息
                            this.refreshItems();
                            this.addCommodityVisible=false;//关闭添加对话框
                        }else{
                            this.$message({
                                message: '添加失败',
                                type: 'error'
                            });
                            return false;
                        }
                    } else {
                        this.$message({
                            message: '请输入正确的添加格式',
                            type: 'warning'
                        });
                        return false;
                    }
                });
            },//在添加商品对话框中 确认添加商品
            resetAppend(){
                this.$refs.addCommodityName.resetFields;
            },//在添加商品对话框中 重置添加商品
            deleteOne(row){
                let _this=this;
                this.$confirm('是否删除：'+row.nameCommodity,'删除数据',{
                    confirmButtonText:'确定',
                    cancelButtonText:'取消',
                    type:'warning'
                }).then(()=>{
                    this.$http.delete('http://localhost:8181/commodity/delete/'+row.idCommodity).then(function (response) {
                        if(response.data){
                            _this.$alert(row.nameCommodity+'删除成功','删除数据',{
                                confirmButtonText:'确定',
                                callback:action => {
                                    location.reload()
                                }
                            })
                        }
                    })
                });

            },//删除一个商品
            editOne(row){
                this.editCommodityVisible=true;
                this.commodityBeforeEdit.idCommodity=row.idCommodity;
                this.commodityBeforeEdit.nameCommodity=row.nameCommodity;
                this.commodityBeforeEdit.specifications=row.specifications;
                this.commodityBeforeEdit.quantity=row.quantity;
                this.commodityBeforeEdit.purchasePrice=row.purchasePrice;
                this.commodityBeforeEdit.salePrice=row.salePrice;
                this.commodityBeforeEdit.vipPrice=row.vipPrice;
                this.commodityToEdit=this.commodityBeforeEdit;
            },//显示商品编辑框，并获得当前row的基本数据
            confirmEdit(){
                this.$refs.editCommodityName.validate(async valid=>{
                    if (valid){
                        const {data:res}=await this.$http.put('/commodity/update',this.commodityToEdit);
                        console.log(res);
                        if(res){
                            this.$message.success('修改成功');
                            this.refreshItems();
                            this.editCommodityVisible=false;//关闭登录对话框
                        }else{
                            this.$message({
                                message: '登录失败',
                                type: 'error'
                            });
                            return false;
                        }
                    } else {
                        this.$message({
                            message: '请填入正确的修改格式！',
                            type: 'warning'
                        });
                        return false;
                    }
                });
            },//商品编辑框，确认修改
            refreshItems(){
                let _this=this;
                this.$http.get('commodity/list').then(function (response) {
                    _this.items=response.data;
                })
            },//重新从后端请求数据，用于刷新表格
            refreshFullItems(){
                let _this=this;
                this.$http.get('commodity/list').then(function (response) {
                    _this.fullitems=response.data;
                })
            }//重新生成完全数组
        }
    }

</script>

<style scoped>
    #commodityHeader{
        width: 103.5%;
        margin-left: -20px;
        margin-top: -20px;
        background: #C0C4CC;
        box-shadow: 0 0 5px ;
    }
    #commodityTable{
        margin-top: 10px;
    }
    #commodityAppend{
        font-size: small;
    }

</style>