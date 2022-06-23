<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style>
html,
body,
#app {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
.el-table thead tr th .cell {
    color: #333;
    font-weight: 500;
    font-size: 14px;
}

.el-table__body tbody tr td .cell{
    color: #555;
    font-weight: 400;
    font-size: 14px;
}


.el-table--group, .el-table--border {
    border: 0px solid #EBEEF5 !important;
}

</style>
