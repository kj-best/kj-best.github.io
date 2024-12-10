<template>
    <div class="main-container">
        <div class="main-content">
            <slot></slot>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PageBody"
    }
</script>

<style scoped>
    .main-container{
        margin-top: 67px;
        display: flex;
        justify-content: center;
        background-color: #FFF0F5;
    }
    .main-content{
        width: 1000px;
        background-color: #ffffff;
        min-height: 90vh;
        margin-top: 10px;
    }
</style>