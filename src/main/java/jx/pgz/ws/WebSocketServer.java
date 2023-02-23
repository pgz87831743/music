package jx.pgz.ws;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@ServerEndpoint("/api/pushMessage/{userName}")
public class WebSocketServer {

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userName = "";

    /**
     * 连接建立成
     * 功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("userName") String userName) {
        this.session = session;
        this.userName =userName;
        if(webSocketMap.containsKey(userName)){
            webSocketMap.remove(userName);
            //加入set中
            webSocketMap.put(userName,this);
        }else{
            //加入set中
            webSocketMap.put(userName,this);
            //在线数加1
            addOnlineCount();
        }
        log.info("用户连接:"+userName+",当前在线人数为:" + getOnlineCount());
        sendMessage(new HashMap<String,Object>(){{
            put("msg",userName+"连接成功");
        }});
    }

    /**
     * 连接关闭
     * 调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userName)){
            webSocketMap.remove(userName);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:"+ userName +",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消
     * 息后调用的方法
     * @param message
     * 客户端发送过来的消息
     **/
    @OnMessage
    public void onMessage(String message, Session session) {
        webSocketMap.forEach((k,v)->{
            Map<String,Object> map=new HashMap<>();
            map.put("msg",userName+"说:"+message);
            v.sendMessage(map);
        });
    }


    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

        log.error("用户错误:"+this.userName +",原因:"+error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务
     * 器主动推送
     */
    public void sendMessage(Map<String,Object> msg) {
        try {
            this.session.getBasicRemote().sendText(JSON.toJSONString(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获得此时的
     * 在线人数
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 在线人
     * 数加1
     */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    /**
     * 在线人
     * 数减1
     */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
