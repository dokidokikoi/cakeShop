<template>
  <ul>
      <GoodsItem v-for="item in goodsList" :key="item.id" :goods="item" />
  </ul>
</template>

<script>
import GoodsItem from '@/components/goods/GoodsItem'

export default {
    name: "ShowGoods",
    props: ["goodsList"],
    components: {GoodsItem}
}
</script>

<style lang="less" scoped>
    ul {
        list-style-type: none;
        padding: 0;
        margin: 0;

        &::after {
            content: '';
            display: block;
            clear: both;
        }
    }
</style>