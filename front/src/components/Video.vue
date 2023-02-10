<template>
  <div id="player">
    <NPlayer :options="options" :set="setPlayer" />
    <div>正在观看人数：{{ watchNumber }}</div>
  </div>
</template>
<script>
import Danmaku from '@nplayer/danmaku'
import { commitBarage } from '../require/api/barrage'
import io from "socket.io-client";
let player;
let socket;
export default {
  setup() {
    return {
      setPlayer: (p) => {
        socket = io.connect("ws://localhost:7000", { transports: ['websocket'] })
        socket.on("get", (data) => {
          console.log(data)
        })//收到弹幕
        player = p;


        //发送实时弹幕
        player.on('DanmakuSend', (opts) => {
          const data = {
            text: opts.text,
            time: opts.time,
            createTime: new Date().valueOf()
          }
          socket.emit("commit", data)
        })
        return player;
      }
    };
  },
  data() {
    return {
      watchNumber: 0,
      options: {
        src: 'https://stupid-dragon.oss-cn-beijing.aliyuncs.com/vue-video-danmaku/d.mp4',
        plugins: [
          new Danmaku({
          })
        ]
      }
    }
  },
  mounted() {
  }
}
</script>
<style scoped>
#player {
  max-width: 960px;
  height: 620px;
  margin: 0 auto;
}
</style>