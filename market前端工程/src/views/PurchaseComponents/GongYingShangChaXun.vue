<template>
    <div>
        <el-header id="SupplierHeader">

        </el-header>
        <el-table
                id="SupplierTable"
                max-height="430px"
                :data="suppliers"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="idSupplier"
                    label="供应商ID"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="idCommodity"
                    label="商品ID"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="purchasePrice"
                    label="进货价格"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="nameSupplier"
                    label="供货商名称"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="contact"
                    label="供货商联系人"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="telephone"
                    label="固定电话"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="移动电话"
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
                    prop="creditCard"
                    label="银行卡号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="creditBank"
                    label="开户银行"
                    width="150">
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "GongYingShangChaXun",
        created() {
            let _this=this;
            this.$http.get('http://localhost:8181/supplier/list').then(function (response) {
                _this.suppliers=response.data;
            });

        },
        data(){
            return{

                suppliers:[
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
                ],
                supplierEdit:{
                    idSupplier : "",
                    idCommodity: "",
                    purchasePrice: "",
                    nameSupplier: "",
                    contact: "",
                    telephone: "",
                    phone: "",
                    address: "",
                    postcode: "",
                    creditCard: "",
                    creditBank: ""
                }
            }
        },
        methods:{

        }
    }
</script>

<style scoped>
    #SupplierHeader{
        width: 104%;
        margin-left: -20px;
        margin-top: -20px;
        background: #C0C4CC;
        box-shadow: 0 0 5px ;
    }
    #SupplierTable{
        margin-top: 10px;
    }
</style>