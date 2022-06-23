<template>
  <el-container class="layout-container-demo">
    <el-aside>
      <el-scrollbar>
        <el-menu
          :class="
            isCollapse == true
              ? 'el-menu-vertical'
              : 'el-menu-vertical-Collapse'
          "
          :router="true"
          :default-active="this.$route.path"
          :collapse="isCollapse"
          @open="handleOpen"
          @select="selectItem"
          @close="handleClose"
        >
          <div class="title" @click="goMain">
            <img :src="logo" /><span
              :class="isCollapse == true ? 'hide-title' : 'show-title'"
              >报修管理系统</span
            >
          </div>
          <el-sub-menu :index="item.id" v-for="item in menus" :key="item.id">
            <template #title>
              <el-icon> <component :is="item.icon" /> </el-icon>

              <span>{{ item.title }}</span>
            </template>
            <el-menu-item-group>
              <el-menu-item
                :index="itm.path"
                v-for="itm in item.subMenus"
                :key="itm.id"
                >{{ itm.title }}</el-menu-item
              >
            </el-menu-item-group>
          </el-sub-menu>
        </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-header>
        <div class="toolbar">
          <div class="right-top">
            <div class="operate-icon">
              <el-icon
                :class="
                  isCollapse == true
                    ? 'hide-title collapse-open'
                    : 'show-title collapse-open'
                "
                @click="close"
                ><Fold
              /></el-icon>
              <el-icon
                :class="
                  isCollapse == false
                    ? 'hide-title collapse-open'
                    : 'show-title collapse-open'
                "
                @click="open"
                ><Expand
              /></el-icon>
              <div>
                <el-breadcrumb separator="/">
                  <el-breadcrumb-item v-for="item in breadcrumbs">{{
                    item
                  }}</el-breadcrumb-item>
                </el-breadcrumb>
              </div>
            </div>
            <div class="right-top-logout">
              <el-popconfirm
                title="确定要退出登录吗?"
                confirmButtonText="确定"
                cancelButtonText="取消"
                @confirm="logout"
              >
                <template #reference> {{ user.userName }} </template>
              </el-popconfirm>
            </div>
          </div>
        </div>
      </el-header>
      <el-main>
        <el-scrollbar class="scrollbar">
          <keep-alive>
            <router-view></router-view>
          </keep-alive>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import {
  Management,
  User,
  UserFilled,
  Fold,
  Expand,
  Setting,
} from "@element-plus/icons-vue";

export default {
  name: "index",
  components: { Management, User, UserFilled, Fold, Expand, Setting },
  mounted() {
    this.user = JSON.parse(localStorage.getItem("user"));
    let path = this.$route.path;
    for (let i = 0; i < this.menus.length; i++) {
      for (let j = 0; j < this.menus[i].subMenus.length; j++) {
        if (this.menus[i].subMenus[j].path == path) {
          this.breadcrumbs.push(this.menus[i].title);
          this.breadcrumbs.push(this.menus[i].subMenus[j].title);
          break;
        }
        break;
      }
    }
  },
  data() {
    return {
      activeIndex: 1,
      isCollapse: false,
      user: "",
      logo: require("../assets/images/DM.png"),
      menus: [
        {
          icon: "Management",
          title: "报修管理",
          id: 1,
          subMenus: [
            {
              id: 11,
              title: "报修信息",
              path: "/indexView/repairInfoListView",
            },
          ],
        },
        {
          icon: "User",
          title: "用户管理",
          id: 2,
          subMenus: [
            {
              id: 21,
              title: "管理员信息",
              path: "/indexView/sysUserListView",
            },
          ],
        },
        {
          icon: "UserFilled",
          title: "微信用户管理",
          id: 3,
          subMenus: [
            {
              id: 31,
              title: "微信用户信息",
              path: "/indexView/wxUserListView",
            },
          ],
        },
      ],
      breadcrumbs: ["报修管理系统"],
    };
  },
  methods: {
    open() {
      this.isCollapse = false;
    },
    close() {
      this.isCollapse = true;
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    logout() {
      localStorage.removeItem("user");
      localStorage.removeItem("token");
      this.$router.replace({ path: "/" });
    },
    goMain() {
      this.$router.replace({ path: "/" });
              this.breadcrumbs = ["报修管理系统"];

    },
    selectItem(index, indexPath, item) {
      if (indexPath.length) {
        this.breadcrumbs = [];
        let path = indexPath[1];
        for (let i = 0; i < this.menus.length; i++) {
          for (let j = 0; j < this.menus[i].subMenus.length; j++) {
            if (this.menus[i].subMenus[j].path == path) {
              this.breadcrumbs.push(this.menus[i].title);
              this.breadcrumbs.push(this.menus[i].subMenus[j].title);
              break;
            }
            break;
          }
        }
      }
    },
  },
};
</script>
<style>
.body {
  display: flex;
}
.right-view {
  height: 100vh;
  width: 100%;
}
.title {
  color: white;
  font-size: 18px;
  display: flex;
  text-align: center;
  padding-top: 4px;
  padding-bottom: 2px;
  cursor: pointer;
}
.title img {
  width: 60px;
  height: 52.5px;
  flex-shrink: 0;
}
.title span {
  line-height: 52.5px;
}
.el-menu-vertical {
  height: 100vh;
  background: #3070f0;
}

.el-menu-vertical-Collapse {
  height: 100vh;
  width: 202px;
  background: #3070f0;
}
.el-sub-menu__title {
  color: white;
}
.el-sub-menu__title:hover {
  color: white;
  background: #1757d6;
}
.el-menu-item-group__title {
  padding: 0;
}
.el-menu-item.is-active {
  color: white;
  background: #1a60eb;
  padding-left: 60px !important;
}
.el-menu-item:before {
  content: "";
  width: 4px;
  height: 4px;
  border-radius: 50%;
  border: 1px solid white;
  position: absolute;
  left: 45px;
  top: 23px;
}
.el-menu-item.is-active:before {
  content: "";
  width: 6px;
  height: 6px;
  border-radius: 50%;
  border: none;
  background: white;
  position: absolute;
  left: 45px;
  top: 23px;
}

.el-main {
  padding: 0px;
  background: #f2f2f2;
}
.collapse-open {
  cursor: pointer;
  padding: 10px;
  font-size: 20px;
  color: #3070f0;
  line-height: 52px;
}
.el-menu-item:hover {
  color: white;
  background: #1757d6;
}
.el-menu-item {
  color: white;
  background: #3070f0;
  height: 50px;
  line-height: 50px;
  padding: 0 45px;
  min-width: 200px;
  padding-left: 60px !important;
}
.hide-title {
  display: none;
}
.show-title {
  display: inline;
}
.operate-icon {
  margin-top: 5px;
  display: flex;
}
.right-top {
  display: flex;
}
.right-top-logout {
  margin-left: auto;
  /* float: right; */
  line-height: 52px;
  padding-right: 20px;
  cursor: pointer;
  color: #3070f0;
}
.el-popconfirm__action {
  margin-top: 10px;
}
.el-aside {
  --el-aside-width: auto;
}
.el-header {
  border-bottom: 1px solid #3070f0;
  --el-header-height: 52px;
  padding: 0 10px;
  box-sizing: border-box;
  flex-shrink: 0;
}
.scrollbar {
  height: calc(100vh - 57px);
}
.el-breadcrumb {
  font-size: 14px;
  line-height: 40px;
}
</style>

