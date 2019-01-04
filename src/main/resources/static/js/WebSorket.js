		//重连次数
		var count = 0;
		//是否重连
		var type = false;
		//WebSocket属性
		var ws;
		//心跳
		var timer;
	// 新建通讯，必须是WS协议
		function initSocket() {
			if(window.WebSocket) {
				//传参采用RSTL风格
				ws = new WebSocket("ws://127.0.0.1:8080/ws/sys");  
				//通讯状态
				ws.onopen = function() {
					console.debug("长连接建立成功，可以通讯");
					count = 0;
					heartbeat();//启动心跳程序
					if(type) {
						$('#myModal').modal('hide');
						$("#send").prop("disabled",false);
						type = false;
					}
				}
				ws.onclose = function() {
					console.debug("长连接关闭");
					type = true;
					anewOpen();
					$("#send").prop("disabled", "disabled");
				}
				ws.onerror = function() {
					console.debug("通讯异常");
				}
				ws.onmessage = function(e) {
					var msg = e.data; // 服务器返回的消息
					if(msg == "Over"){
						count = 11;
					} else {
						var obj = $.parseJSON(msg);
						if("user" == obj.type) {
							$(".row:eq(1)").html("");
							$(".row:eq(2)").html("");
							$(".row:eq(3)").html("");
							$.each(obj.content,function(i,data){
								if(data.type == 1) {
									if(data.aide.type == 1) {
										ht = "<button type=\"button\" onclick=\"cut('"+data.aide.name+"',"+data.aide.id+")\" class=\"btn bg-dark btn-lg btn-block\" style=\"height: 50px;color:white;width: 310px;margin-top: 0px;border-bottom: 1px solid black;padding-top:3px ;\">"+
										    	"<div style=\"position: relative;left: -50px;\">"+
										    		"<img src=\"/img/"+data.aide.imgName+"\" class=\"img-rounded img-responsive center-block\" width=\"40\" height=\"40\" style=\"border-radius: 5px;\">"+
										    		"<span style=\"color: white;font-size: 14px;line-height: 20px;margin-left: 5px;vertical-align:top;\">"+data.aide.name+"</span>"+
										    		"<span style=\"font-size: 10px;position: relative;left: 100px;\">—>点击联系</span>"+
										    	"</div>"+
										    "</button>";
										$(".row:eq(1)").append(ht);
									} else {
										ht = "<p class=\"bg-dark\" style=\"height: 50px;width: 310px;padding-left:20px;padding-top: 4px;margin-bottom: 0px;border-bottom: 1px solid black;\">"+
									    		"<img src=\"/img/"+data.aide.imgName+"\" class=\"img-rounded img-responsive center-block\" width=\"40\" height=\"40\" style=\"border-radius: 5px;\">"+
									    		"<span style=\"color: white;font-size: 14px;line-height: 20px;margin-left: 5px;vertical-align:top;\">"+data.aide.name+"</span>"+
									    	"</p>";
										$(".row:eq(2)").append(ht);
									}
								} else {
									ht = "<p class=\"bg-dark\" style=\"height: 50px;width: 310px;padding-left:20px;padding-top: 4px;margin-bottom: 0px;border-bottom: 1px solid black;\">"+
								    		"<img src=\"/img/"+data.aide.imgName+"\" class=\"img-rounded img-responsive center-block\" width=\"40\" height=\"40\" style=\"border-radius: 5px;\">"+
								    		"<span style=\"color: white;font-size: 14px;line-height: 20px;margin-left: 5px;vertical-align:top;\">"+data.aide.name+"</span>"+
								    	 "</p>";
									$(".row:eq(3)").append(ht);
								}
							})
						} else if("message" == obj.type) {
							//<!-- 回复方 -->
							var ht = "<li style=\"margin-top: 10px;\" class=\"bubble-you\">"+
								"<img src=\"img/TestPng.png\" class=\"img-rounded img-responsive center-block\" width=\"40\" height=\"40\" style=\"border-radius: 40px;vertical-align: top;margin-right: 5px;\">"+
								"<p>"+
									"<span>"+msg+"</span>"+
								"</p>"+
							"</li>";
							$("#Show ul").append(ht);
							alert(msg);
						}
					}
					
					
					/*
					<!-- 发送 方-->
					<li style="margin-top: 10px;" class="bubble-me">
						<p>
							<span>
								你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！你好二货！！！！
							</span>
						</p>
						<img src="img/TestPng.png" class="img-rounded img-responsive center-block" style="vertical-align: top;margin-left: 5px;border-radius: 40px;" width="40" height="40"  >
					</li>
				</ul>*/
				}
			} else {
				alert("不支持WebSocket技术");
			}
		}
		//重连
		function anewOpen() {
			$('#myModal').modal({backdrop:'static', keyboard: false}); 
			if(count < 10) {
				$('#myModal').modal('show');
				count++;
				$("#myModal .modal-body").html("重连中" + count + "/10次，正在重连....");
				setTimeout(function() {
					initSocket();
				},5*1000);
			} else if(count == 10 ) {
				$("#myModal .modal-body").html("无法连接服务器，已断开连接...");
			} else if(count == 11) {
				alert("已断开连接..请重新进行登陆");
				window.location = "/c/user/go";
			}
		}
		//发送消息
		function Send() {
			if(ws.readyState == 1) {
				ws.send($("#MessageTxet").val());
			} else {
					$("#send").prop("disabled", "disabled");
					$('#myModal').modal('show');
					$("#myModal .modal-body").html("已断开连接");
			}
		}
		//心跳检测,为了解决scoket假死
		function heartbeat() {
			clearInterval(timer);
			timer = window.setInterval(function() {
				console.debug("客户端心跳程序运行");
				if(ws.readyState == 1) {
					ws.send("ping");
				} else {
					anewOpen();
				}
			}, 15000); //建议4分半
		}
		