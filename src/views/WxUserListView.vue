<template>
  <addEditWxUserView ref="addEditWxUserView" />
  <addFileView ref="addFileView" />
  <el-main>
    <div class="teacher">
      <div class="app-table">
        <div class="app-body">
          <div class="app-search">
            <el-form :inline="true" class="demo-form-inline" v-model="form">
             
              <el-form-item label="昵称">
                <el-input
                  placeholder="请输入昵称"
                  v-model="form.nickName"
                ></el-input>
              </el-form-item>
              <el-form-item>
                <el-button
                  plain
                  type="primary"
                  @click="onSubmit"
                  @keyup.enter="searchEnter"
                  >搜索</el-button
                >
                <el-button plain type="warning" @click="exportExcel"
                  >导出报表</el-button
                >
                <el-button plain type="danger" @click="importExcel"
                  >批量导入<el-icon class="el-icon--right"><Upload /></el-icon
                ></el-button>
              </el-form-item>
            </el-form>
          </div>
          <el-table
            size="small"
            :data="list"
            style="width: 100%"
            border
            :row-class-name="tableRowClassName"
          >
            <el-table-column prop="id" label="序号" width="90px"/>
            <el-table-column prop="nickName" label="昵称" />
            <el-table-column prop="language" label="语言" />
            <el-table-column prop="city" label="城市" />
            <el-table-column prop="gender" label="性别" />
            <el-table-column prop="province" label="省份" />
            <el-table-column prop="avatarUrl" label="头像" />
            <el-table-column label="操作" width="140px">
              <template style="display: block" #default="scope">
              
                <el-button
                  plain
                  size="small"
                  type="danger"
                  @click="deleteWxUser(scope.row.id)"
                  >删除
                </el-button>
              </template>
            </el-table-column>
            <template v-slot:empty>
              <el-empty description="暂无数据"></el-empty>
            </template>
          </el-table>
          <el-config-provider :locale="locale">
            <el-pagination
              :total="total"
              :page-size="limit"
              :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper"
              @size-change="changePageSize"
              @current-change="changeCurrentPage"
            >
            </el-pagination>
          </el-config-provider>
        </div>
      </div>
    </div>
  </el-main>
</template>
<script>
import addEditWxUserView from "@/components/addEditWxUser.vue";
import addFileView from "@/components/addExcelFile.vue";
import { ElConfigProvider, ElMessageBox, ElMessage } from "element-plus";
import zhCn from "element-plus/lib/locale/lang/zh-cn";
import { httpService } from "@/utils/httpService";
import { Picture as IconPicture, Upload } from "@element-plus/icons-vue";

export default {
  name: "WxUserListView",
  data() {
    return {
      type: 0,
      title: "",
      page: 0,
      pageSize: 10,
      total: 0,
      limit: 10,
      list: [],
      form: {
        openid: "",
        nickName: "",
        language: "",
        city: "",
        province: "",
        avatarUrl: "",
        email: "",
        unionid: "",
        mobile: "",
        cardNumber: "",
        realName: "",
        otherOpenid: "",
        campus: "",
      },
    };
  },
  components: {
    addEditWxUserView,
    addFileView,
    IconPicture,
    ElConfigProvider,
    ElMessageBox,
    ElMessage,
    Upload,
  },
  setup() {
    return {
      locale: zhCn,
    };
  },
  mounted() {
    this.getWxUserList();
  },
  watch: {
    $route() {
      this.getWxUserList();
    },
  },
  methods: {
    searchEnter: function (e) {
      // 使用 which 和 keyCode 属性来解决兼容问题
      var keyCode = window.event ? e.keyCode : e.which;
      var val = e.target.value;
      if (keyCode == 13 && val) {
        this.getWxUserList();
      }
    },
    add() {
      this.$refs.addEditWxUserView.title = "添加微信用户";
      this.$refs.addEditWxUserView.personnelType = this.type;
      this.$refs.addEditWxUserView.dialogFormVisible = true;
    },
    editWxUser(obj) {
      this.$refs.addEditWxUserView.title = "修改信息";
      this.$refs.addEditWxUserView.getWxUserById(obj.id);
      this.$refs.addEditWxUserView.dialogFormVisible = true;
    },
    deleteWxUser(id) {
      ElMessageBox.confirm("确定要删除本条记录吗？", "提醒", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          httpService("/api/wxUser/deleteById", "id=" + id, "post").then(
            (data) => {
              if (data.code == 200) {
                ElMessage.success("删除成功");
                this.getWxUserList();
              } else {
                ElMessage.error("删除失败，请重试！");
              }
            }
          );
        })
        .catch(() => {});
    },
    reloadData() {
      this.page = 1;
      this.getWxUserList();
    },
    importExcel() {
      this.$refs.addFileView.dialogFormVisible = true;
      this.$refs.addFileView.title = "批量导入微信用户";
      this.$refs.addFileView.uploadApiUrl = "/api/wxUser/importExcel";
    },
    exportExcel() {
      httpService(
        "/api/wxUser/exportExcel",
        {
          openid: this.form.openid,
          nickName: this.form.nickName,
          language: this.form.language,
          city: this.form.city,
          province: this.form.province,
          avatarUrl: this.form.avatarUrl,
          email: this.form.email,
          unionid: this.form.unionid,
          mobile: this.form.mobile,
          cardNumber: this.form.cardNumber,
          realName: this.form.realName,
          otherOpenid: this.form.otherOpenid,
          campus: this.form.campus,
        },
        "get"
      ).then((data) => {
        if (data.code == 200) {
          window.open(data.msg, "_blank");
        }
      });
    },
    onSubmit() {
      this.getWxUserList();
    },
    // 每页记录数改变，size：回调参数，表示当前选中的“每页条数”
    changePageSize(size) {
      this.pageSize = size;
      this.limit = size;
      this.getWxUserList();
    },

    // 改变页码，page：回调参数，表示当前选中的“页码”
    changeCurrentPage(page) {
      this.page = page;
      this.getWxUserList();
    },
    tableRowClassName({ row, rowIndex }) {
      return "";
    },
    getWxUserList() {
      httpService(
        "/api/wxUser/list/" + this.page + "/" + this.pageSize,
        {
          openid: this.form.openid,
          nickName: this.form.nickName,
          language: this.form.language,
          city: this.form.city,
          province: this.form.province,
          avatarUrl: this.form.avatarUrl,
          email: this.form.email,
          unionid: this.form.unionid,
          mobile: this.form.mobile,
          cardNumber: this.form.cardNumber,
          realName: this.form.realName,
          otherOpenid: this.form.otherOpenid,
          campus: this.form.campus,
        },
        "get"
      ).then((data) => {
        this.list = data.list;
        this.total = data.total;
        this.pageCount = data.pages;
      });
    },
  },
};
</script>
<style scoped>
.el-main {
  padding: 0px;
}

.teacher-content {
  width: 100%;
}

.app-table {
  background: #eff3f5;
  padding: 10px;
}

.app-search {
  padding-top: 10px;
  /* border-top: 1px solid #dedede; */
  /* margin-top: 20px; */
}

.app-separator {
  padding-top: 10px;
  padding-bottom: 20px;
  padding-left: 10px;
}

.app-body {
  background: white;
  padding: 20px 20px 10px 20px;
  margin-bottom: 10px;
}

.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-lighter);
}

.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-lighter);
}

.el-pagination {
  text-align: center;
  margin-top: 10px;
}
</style>

