<template>
    <el-dialog v-model="dialogFormVisible" :title="title" @close="closeDialog">
        <el-form :model="addWxUserForm" ref="addWxUserRef" :rules="addWxUserrules">
                        <el-form-item label="openid" :label-width="formLabelWidth"
                                      prop="openid">
                            <el-input
                                    v-model="addWxUserForm.openid"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写openid"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="昵称" :label-width="formLabelWidth"
                                      prop="nickName">
                            <el-input
                                    v-model="addWxUserForm.nickName"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写昵称"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="语言" :label-width="formLabelWidth"
                                      prop="language">
                            <el-input
                                    v-model="addWxUserForm.language"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写语言"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="用户id" :label-width="formLabelWidth"
                                      prop="uId" v-if="addWxUserForm.id">
                            <el-input
                                    v-model="addWxUserForm.uId"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写用户id"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="城市" :label-width="formLabelWidth"
                                      prop="city">
                            <el-input
                                    v-model="addWxUserForm.city"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写城市"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="性别" :label-width="formLabelWidth"
                                      prop="gender" v-if="addWxUserForm.id">
                            <el-input
                                    v-model="addWxUserForm.gender"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写性别"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="省份" :label-width="formLabelWidth"
                                      prop="province">
                            <el-input
                                    v-model="addWxUserForm.province"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写省份"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="头像" :label-width="formLabelWidth"
                                      prop="avatarUrl">
                            <el-input
                                    v-model="addWxUserForm.avatarUrl"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写头像"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="创建日期" :label-width="formLabelWidth"
                                      prop="createDate" v-if="addWxUserForm.id">
                            <el-input
                                    v-model="addWxUserForm.createDate"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写创建日期"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="邮箱" :label-width="formLabelWidth"
                                      prop="email">
                            <el-input
                                    v-model="addWxUserForm.email"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写邮箱"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="unionid" :label-width="formLabelWidth"
                                      prop="unionid">
                            <el-input
                                    v-model="addWxUserForm.unionid"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写unionid"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="创建时间" :label-width="formLabelWidth"
                                      prop="createTime" v-if="addWxUserForm.id">
                            <el-input
                                    v-model="addWxUserForm.createTime"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写创建时间"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="是的可用" :label-width="formLabelWidth"
                                      prop="isDelete" v-if="addWxUserForm.id">
                            <el-input
                                    v-model="addWxUserForm.isDelete"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写是的可用"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="类型" :label-width="formLabelWidth"
                                      prop="type" v-if="addWxUserForm.id">
                            <el-input
                                    v-model="addWxUserForm.type"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写类型"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="联系电话" :label-width="formLabelWidth"
                                      prop="mobile">
                            <el-input
                                    v-model="addWxUserForm.mobile"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写联系电话"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="用户号码" :label-width="formLabelWidth"
                                      prop="cardNumber">
                            <el-input
                                    v-model="addWxUserForm.cardNumber"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写用户号码"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="真实姓名" :label-width="formLabelWidth"
                                      prop="realName">
                            <el-input
                                    v-model="addWxUserForm.realName"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写真实姓名"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="是否绑定手机" :label-width="formLabelWidth"
                                      prop="isBindingMobile" v-if="addWxUserForm.id">
                            <el-input
                                    v-model="addWxUserForm.isBindingMobile"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写是否绑定手机"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="服务号openId" :label-width="formLabelWidth"
                                      prop="otherOpenid">
                            <el-input
                                    v-model="addWxUserForm.otherOpenid"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写服务号openId"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="单位id" :label-width="formLabelWidth"
                                      prop="campus">
                            <el-input
                                    v-model="addWxUserForm.campus"
                                    autocomplete="off"
                                    ref="name"
                                    placeholder="请填写单位id"
                            ></el-input>
                        </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="cancel()">取消</el-button>
        <el-button type="primary" @click="save()">保存</el-button>
      </span>
        </template>
    </el-dialog>
</template>

<script>
    import {ref} from "vue";
    import {httpService} from "@/utils/httpService";
    import {ElMessage, ElForm} from "element-plus";
    import {vxRule} from "@/utils/validator";

    export default {
        name: "AddWxUser",
        components: {ElForm, ElMessage},
        data() {
            return {
                dialogFormVisible: ref(false),
                formLabelWidth: "120px",
                title: "",
                personnelType: "",
                addWxUserForm: {
                    openid: "",
                    nickName: "",
                    language: "",
                    uId: "",
                    city: "",
                    gender: "",
                    province: "",
                    avatarUrl: "",
                    createDate: "",
                    email: "",
                    unionid: "",
                    createTime: "",
                    isDelete: "",
                    type: "",
                    mobile: "",
                    cardNumber: "",
                    realName: "",
                    isBindingMobile: "",
                    otherOpenid: "",
                    campus: "",
                },
                // 校验规则
                addWxUserrules: {
                    openid: vxRule(true, null, "blur"),
                    nickName: vxRule(true, null, "blur"),
                    language: vxRule(true, null, "blur"),
                    city: vxRule(true, null, "blur"),
                    province: vxRule(true, null, "blur"),
                    avatarUrl: vxRule(true, null, "blur"),
                    email: vxRule(true, null, "blur"),
                    unionid: vxRule(true, null, "blur"),
                    mobile: vxRule(true, null, "blur"),
                    cardNumber: vxRule(true, null, "blur"),
                    realName: vxRule(true, null, "blur"),
                    otherOpenid: vxRule(true, null, "blur"),
                    campus: vxRule(true, null, "blur"),
                },
            };
        },
        methods: {
            closeDialog() {
                this.clear();
            },
            cancel() {
                this.dialogFormVisible = false;
                this.clear();
            },
            getWxUserById(id) {
                httpService("/api/wxUser/queryById", {id: id}, "get").then(
                    (data) => {
                        this.addWxUserForm = data;
                    }
                );
            },
            clear() {
                this.dialogFormVisible = false;
                //清空form
                    this.addWxUserForm.id = "";
                    this.addWxUserForm.openid = "";
                    this.addWxUserForm.nickName = "";
                    this.addWxUserForm.language = "";
                    this.addWxUserForm.uId = "";
                    this.addWxUserForm.city = "";
                    this.addWxUserForm.gender = "";
                    this.addWxUserForm.province = "";
                    this.addWxUserForm.avatarUrl = "";
                    this.addWxUserForm.createDate = "";
                    this.addWxUserForm.email = "";
                    this.addWxUserForm.unionid = "";
                    this.addWxUserForm.createTime = "";
                    this.addWxUserForm.isDelete = "";
                    this.addWxUserForm.type = "";
                    this.addWxUserForm.mobile = "";
                    this.addWxUserForm.cardNumber = "";
                    this.addWxUserForm.realName = "";
                    this.addWxUserForm.isBindingMobile = "";
                    this.addWxUserForm.otherOpenid = "";
                    this.addWxUserForm.campus = "";
            },
            save() {
                this.$refs.addWxUserRef.validate((valid) => {
                    if (valid) {
                        let params = new FormData(); //创建一个form对象,必须是form对象否则后端接受不到数据
                        params.append("openid", this.addWxUserForm.openid);
                        params.append("nickName", this.addWxUserForm.nickName);
                        params.append("language", this.addWxUserForm.language);
                        params.append("uId", this.addWxUserForm.uId);
                        params.append("city", this.addWxUserForm.city);
                        params.append("gender", this.addWxUserForm.gender);
                        params.append("province", this.addWxUserForm.province);
                        params.append("avatarUrl", this.addWxUserForm.avatarUrl);
                        params.append("createDate", this.addWxUserForm.createDate);
                        params.append("email", this.addWxUserForm.email);
                        params.append("unionid", this.addWxUserForm.unionid);
                        params.append("createTime", this.addWxUserForm.createTime);
                        params.append("isDelete", this.addWxUserForm.isDelete);
                        params.append("type", this.addWxUserForm.type);
                        params.append("mobile", this.addWxUserForm.mobile);
                        params.append("cardNumber", this.addWxUserForm.cardNumber);
                        params.append("realName", this.addWxUserForm.realName);
                        params.append("isBindingMobile", this.addWxUserForm.isBindingMobile);
                        params.append("otherOpenid", this.addWxUserForm.otherOpenid);
                        params.append("campus", this.addWxUserForm.campus);
                        if (this.addWxUserForm.id) {
                            params.append("id", this.addWxUserForm.id);
                            httpService("/api/wxUser/edit", params, "post").then(
                                (data) => {
                                    if (data.code == 200) {
                                        ElMessage.success("修改成功");
                                        this.clear();
                                        //刷新父组件的数据
                                        this.$parent.getWxUserList();
                                    } else {
                                        ElMessage.error("修改失败");
                                    }
                                }
                            );
                        } else {
                            httpService("/api/wxUser/add", params, "post").then(
                                (data) => {
                                    if (data.code == 200) {
                                        ElMessage.success("添加成功");
                                        this.clear();
                                        //刷新父组件的数据
                                        this.$parent.getWxUserList();
                                    } else {
                                        ElMessage.error("添加失败");
                                    }
                                }
                            );
                        }
                    } else {
                        return false;
                    }
                });
            },
        },
    };
</script>
<style>
</style>