var urlpath = "http://lile.vbooline.com/lile-website/";
var share = "";
$(function(){
	
	$.get(
		
		urlpath+"weChat/getTicket?url="+location.href.split('#')[0].replace(new RegExp(/(&)/g), '%26'),
		
		 function(data) {
			share = data.shareUrl;
        //alert(JSON.stringify(data));
			wx.config({

				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。

				appId : data.data.module.appId, // 必填，公众号的唯一标识

				timestamp : data.data.module.timestamp, // 必填，生成签名的时间戳

				nonceStr : data.data.module.noncestr, // 必填，生成签名的随机串

				signature : data.data.module.sign,// 必填，签名，见附录1

				jsApiList : [ "startRecord", "stopRecord", "onVoiceRecordEnd",
						"playVoice", "pauseVoice", "stopVoice",
						"onVoicePlayEnd", "uploadVoice", "downloadVoice",
						"onMenuShareTimeline", "onMenuShareAppMessage" ]
			// 必填，需要使用的JS接口列表，所有JS接口列表见附录2

			});
			

		}
	)
})


 wx.ready(function () {	//alert(share)
	                            // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	                            wx.onMenuShareTimeline({	
	                                title: "利乐上海Fic点位", // 分享标题
	                                link:  share, // 分享链接
	                                imgUrl: urlpath+'ui/img/logo.png', // 分享图标
	                                success: function () {
	
	                                    // 用户确认分享后执行的回调函数

	                                },
	
	                                cancel: function () {
	
	                                    // 用户取消分享后执行的回调函数
	                                    // alert("小伙子没有分享");
	                                }
	
	                            });
	
	                            wx.onMenuShareAppMessage({
	                                title: "利乐上海Fic点位", // 分享标题
	                                desc: '', // 分享描述
	                                link: share, // 分享链接
	                                imgUrl: urlpath+'ui/img/logo.png', // 分享图标
	                                type: 'link', // 分享类型,music、video或link，不填默认为link
	                                dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	                                success: function () {
	
	                                    // 用户确认分享后执行的回调函数

	
	                                },
	
	                                cancel: function () {
	
	                                    // 用户取消分享后执行的回调函数
	
	                                }
	
	                            });
 });