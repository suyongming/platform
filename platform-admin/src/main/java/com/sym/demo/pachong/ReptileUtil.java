//package com.sym.demo.pachong;
//
//import cn.hutool.core.util.ObjectUtil;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.message.BasicHeader;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * @description
// * @Author: sym
// * @Date: 2021/6/2 18:02
// */
//public class ReptileUtil {
//    // 小红书正则
//    private static final String hs_username_regex = "<span.*?class=\\\"name-detail\\\".*?>(.*?)<";
//    private static final String hs_fans_regex = "utf-8\"><meta name=\"description\" content=\".*?有(.*?)位粉丝";
//    private static final String hs_head_regex = " loaded\"><img src=\"(.*?)\" class=\"lazyload lazyload-image loaded\" style=\"width:160px;height:160px";
//
//    // 微博正则
//    private static final String weibo_username_regex = "<h1 class=\\\\\"username\\\\\">(.*?)<";
//    private static final String weibo_fans_regex = "的粉丝\\((.*?)\\)<";
//    private static final String weibo_head_regex = "class=\\\\\"photo_wrap\\\\\">.*?<img.*?src=\\\\\"(.*?)\\\\\" alt=.*? class=\\\\\"photo\\\\\">";
//
//    // 抖音正则  https://v.douyin.com/euJoGGH/
//    // 应通过抖音官方途径获取sec_uid  再去获取数据
//    private static final String tiktok_uec_uid_regex = "sec_uid=(.*?)\\;";
//    // 结尾必须有/
//    private static final String tiktok_user_info_api = "https://www.iesdouyin.com/web/api/v2/user/info/";
//
//    // https://space.bilibili.com/402889865  PC端登录后 进入个人主页 复制出来链接
//    // 哔哩哔哩 正则 可以通过个人主页获取 名字和头像,抓不到粉丝
//    private static final String bili_username_regex = "<title>(.*?)的个人空间 - 哔哩哔哩 ";
//    private static final String bili_fans_regex = "的粉丝\\((.*?)\\)<";
//    private static final String bili_head_regex = "link rel=\"apple-touch-icon\" href=\"(.*?)\"><";
//
//    // 抖音火山 https://share.huoshan.com/hotsoon/s/k7LoeyvYOi8/
//    private static final String hs_to_user_id_regex = "to_user_id=(.*?)\\;";
//    private static final String hs_userinfo_api = "https://share.huoshan.com/api/user/info";
//
//    // todo 快手 https://v.kuaishou.com/dSBYWL 有时候能抓到用户名,如果生成的链接需要尽快使用  cookie取不到
//    private static final String kuaishou_username_regex = "<head><meta charset=UTF-8><title>(.*?)的主页-快手直播</title> <meta";
//    private static Map<String, String> kuaishou_userinfo_query = new HashMap() {{
//        put("operationName", "sensitiveUserInfoQuery");
//        put("query", "query sensitiveUserInfoQuery($principalId: String) {\n  sensitiveUserInfo(principalId: $principalId) {\n    kwaiId\n    originUserId\n    constellation\n    cityName\n    counts {\n      fan\n      follow\n      photo\n      liked\n      open\n      playback\n      private\n      __typename\n    }\n    __typename\n  }\n}\n");
//    }};
//
//    // 大众点评抓取
//    private static final String dazhong_userinfo_regex = "Owl\\.addLog\\(\\'(.*?)\\'\\)";
//    private static final String dazhong_nickname_regex = "nickName = \"(.*?)\"  ";
//
//    // 西瓜视频抓取
//    private static final String xigua_username_regex = "<title data-react-helmet=\"true\">(.*?)的个人主页";
//    private static final String xigua_fans_regex = "</span><span>关注</span></div><div class=\"userDetailV3__header__data\"><span>(.*?)</span><span>粉丝</span></div>";
//    private static final String xigua_head_regex = "<img class=\"tt-img BU-MagicImage\" src=\"(.*?)\" data-src=";
//
//    public static void main(String[] args) {
//        String html = HttpUtil.get("https://v.kuaishou.com/dSBYWL",
//                null,
//                new BasicHeader[]{
//                        new BasicHeader("cookie", "did=web_fdf5a9e150c65d63096ee53453717108; didv=1622639222342; clientid=3; client_key=65890b29; userId=2193400622"),
//                        new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
//                });
////        String html = cn.hutool.http.HttpUtil.get("https://v.kuaishou.com/dSBYWL");
//
//        System.out.println("html>>>>>>>>>>>>>>>>>>>>>>>>>>"+html);
//        Pattern headCompile = Pattern.compile(dazhong_nickname_regex);
//        Matcher headMatcher = headCompile.matcher(html);
//        while (headMatcher.find()) {
//            System.out.println(headMatcher.group(1));
//        }
//    }
//
//
//    /**
//     * @Description: 小红书抓取
//     * @Param: [url, cookieStr]
//     * @return: com.wmeimob.fastboot.songgong.entity.UserVisitingCard
//     * @Author: sym
//     * @Date: 2021/6/2
//     */
//    public static UserCartReptileVo getHongshu(String url, String cookieStr) {
//        UserCartReptileVo result = new UserCartReptileVo();
//        String html = HttpUtil.get(url,
//                null,
//                new BasicHeader[]{
//                        new BasicHeader("cookie", cookieStr),
//                });
//        // 名字
//        Pattern nicknameCompile = Pattern.compile(hs_username_regex);
//        Matcher nicknameMatcher = nicknameCompile.matcher(html);
//        while (nicknameMatcher.find()) {
//            result.setNickName(nicknameMatcher.group(1));
//        }
//
//        // 粉丝
//        Pattern fansCompile = Pattern.compile(hs_fans_regex);
//        Matcher fansMatcher = fansCompile.matcher(html);
//        while (fansMatcher.find()) {
//            result.setFansNum(fansMatcher.group(1));
//        }
//
//        // 头像
//        Pattern headCompile = Pattern.compile(hs_head_regex);
//        Matcher headMatcher = headCompile.matcher(html);
//        while (headMatcher.find()) {
//            result.setHeadImgUrl(headMatcher.group(1));
//        }
//
//        return result;
//    }
//
//    /**
//     * @Description: 微博抓取
//     * @Param: [url, cookieStr]
//     * @return: com.wmeimob.fastboot.songgong.entity.UserVisitingCard
//     * @Author: sym
//     * @Date: 2021/6/2
//     */
//    public static UserCartReptileVo getWeibo(String url, String cookieStr) {
//        UserCartReptileVo result = new UserCartReptileVo();
//        String html = HttpUtil.get(url,
//                null,
//                new BasicHeader[]{
//                        new BasicHeader("cookie", cookieStr),
//                });
//
//        // 名字
//        Pattern nicknameCompile = Pattern.compile(weibo_username_regex);
//        Matcher nicknameMatcher = nicknameCompile.matcher(html);
//        while (nicknameMatcher.find()) {
//            result.setNickName(nicknameMatcher.group(1));
//        }
//
//        // 粉丝
//        Pattern fansCompile = Pattern.compile(weibo_fans_regex);
//        Matcher fansMatcher = fansCompile.matcher(html);
//        while (fansMatcher.find()) {
//            result.setFansNum(fansMatcher.group(1));
//        }
//
//        // 头像
//        Pattern headCompile = Pattern.compile(weibo_head_regex);
//        Matcher headMatcher = headCompile.matcher(html);
//        while (headMatcher.find()) {
//            result.setHeadImgUrl(headMatcher.group(1));
//        }
//
//        return result;
//    }
//
//    /**
//     * @Description: 抖音抓取
//     * @Param: [url, cookieStr]
//     * @return: com.wmeimob.fastboot.songgong.entity.UserVisitingCard
//     * @Author: sym
//     * @Date: 2021/6/2
//     */
//    public static UserCartReptileVo getTiktok(String url, String cookieStr) {
//        UserCartReptileVo result = new UserCartReptileVo();
//        String html = cn.hutool.http.HttpUtil.get(url);
//        Pattern compile = Pattern.compile(tiktok_uec_uid_regex);
//        Matcher matcher = compile.matcher(html);
//        String sec_uid = null;
//        while (matcher.find()) {
//            sec_uid = matcher.group(1);
//        }
//
//        if (StringUtils.isNotEmpty(sec_uid)) {
//            String tiktokUserinfoJson = cn.hutool.http.HttpUtil.get(tiktok_user_info_api + "?sec_uid=" + sec_uid);
//            JSONObject tiktokUserinfo = JSONObject.parseObject(tiktokUserinfoJson).getJSONObject("user_info");
//            if (ObjectUtil.isNotNull(tiktokUserinfo)) {
//                String headImgUrl = tiktokUserinfo.getJSONObject("avatar_larger").getJSONArray("url_list").getString(0);
//                String nickname = tiktokUserinfo.getString("nickname");
//                String fans = tiktokUserinfo.getString("total_favorited");
//                result.setHeadImgUrl(headImgUrl);
//                result.setNickName(nickname);
//                result.setFansNum(fans);
//
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * @Description: 火山抓取
//     * @Param: [url, cookieStr]
//     * @return: com.wmeimob.fastboot.songgong.entity.UserVisitingCard
//     * @Author: sym
//     * @Date: 2021/6/3
//     */
//    public static UserCartReptileVo getHuoshan(String url, String cookieStr) {
//        UserCartReptileVo result = new UserCartReptileVo();
//        String html = cn.hutool.http.HttpUtil.get(url);
//        Pattern compile = Pattern.compile(hs_to_user_id_regex);
//        Matcher matcher = compile.matcher(html);
//        String to_user_id = null;
//        while (matcher.find()) {
//            to_user_id = matcher.group(1);
//        }
//
//        if (StringUtils.isNotEmpty(to_user_id)) {
//            String tiktokUserinfoJson = cn.hutool.http.HttpUtil.get(hs_userinfo_api + "?encrypted_id=" + to_user_id);
//            JSONObject huoshanUserinfo = JSONObject.parseObject(tiktokUserinfoJson).getJSONObject("data");
//            if (ObjectUtil.isNotNull(huoshanUserinfo)) {
//                String headImgUrl = huoshanUserinfo.getString("avatar");
//                String nickname = huoshanUserinfo.getString("nickname");
//                String fans = huoshanUserinfo.getString("fan_ticket_count");
//                result.setHeadImgUrl(headImgUrl);
//                result.setNickName(nickname);
//                result.setFansNum(fans);
//
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * @Description: 哔哩哔哩抓取
//     * @Param: [url, cookieStr]
//     * @return: com.wmeimob.fastboot.songgong.entity.UserVisitingCard
//     * @Author: sym
//     * @Date: 2021/6/2
//     */
//    public static UserCartReptileVo getBili(String url, String cookieStr) {
//        UserCartReptileVo result = new UserCartReptileVo();
//        String html = HttpUtil.get(url,
//                null,
//                new BasicHeader[]{
//                        new BasicHeader("cookie", cookieStr),
//                });
//        // 名字
//        Pattern nicknameCompile = Pattern.compile(bili_username_regex);
//        Matcher nicknameMatcher = nicknameCompile.matcher(html);
//        while (nicknameMatcher.find()) {
//            result.setNickName(nicknameMatcher.group(1));
//        }
//
//        // 粉丝
//        result.setFansNum(null);
//
//        // 头像
//        Pattern headCompile = Pattern.compile(bili_head_regex);
//        Matcher headMatcher = headCompile.matcher(html);
//        while (headMatcher.find()) {
//            result.setHeadImgUrl(headMatcher.group(1));
//        }
//
//        return result;
//    }
//
//    /**
//     * @Description: 快手抓取
//     * @Param: [url, cookieStr]
//     * @return: com.wmeimob.fastboot.songgong.pojo.vo.UserCartReptileVo
//     * @Author: sym
//     * @Date: 2021/6/2
//     */
//    public static UserCartReptileVo getKuaishou(String url, String cookieStr) {
//        UserCartReptileVo result = new UserCartReptileVo();
//        String html = HttpUtil.get(url,
//                null,
//                new BasicHeader[]{
//                        new BasicHeader("cookie", cookieStr),
//                });
//        // 有时能获取到名字
//        Pattern nicknameCompile = Pattern.compile(kuaishou_username_regex);
//        Matcher nicknameMatcher = nicknameCompile.matcher(html);
//        while (nicknameMatcher.find()) {
//            result.setNickName(nicknameMatcher.group(1));
//        }
//
//        // 粉丝
//        result.setFansNum(null);
//
//        // 头像
//        result.setHeadImgUrl(null);
//        return result;
//    }
//
//    /**
//     * @Description: 大众抓取
//     * @Param: [url, cookieStr]
//     * @return: com.wmeimob.fastboot.songgong.pojo.vo.UserCartReptileVo
//     * @Author: sym
//     * @Date: 2021/6/2
//     */
//    public static UserCartReptileVo getDazhong(String url, String cookieStr,String dazhongUserAgent) {
//        UserCartReptileVo result = new UserCartReptileVo();
//        String html = HttpUtil.get(url,
//                null,
//                new BasicHeader[]{
//                        new BasicHeader("cookie", cookieStr),
//                        new BasicHeader("User-Agent", dazhongUserAgent)
//                });
//        Pattern compile = Pattern.compile(dazhong_userinfo_regex);
//        Matcher matcher = compile.matcher(html);
//        String userinfoStr = null;
//        while (matcher.find()) {
//            userinfoStr = matcher.group(1);
//        }
//
//        if(StringUtils.isNotEmpty(userinfoStr)) {
//            JSONObject userinfo = JSONObject.parseObject(userinfoStr);
//            String headImgUrl = userinfo.getString("userFace");
//            String fans = userinfo.getString("fansCount");
//            result.setHeadImgUrl(headImgUrl);
//            result.setFansNum(fans);
//
//        }
//
//        Pattern nicknameCompile = Pattern.compile(dazhong_nickname_regex);
//        Matcher nicknameMatcher = nicknameCompile.matcher(html);
//        while (nicknameMatcher.find()) {
//            result.setNickName(nicknameMatcher.group(1));
//        }
//        return result;
//    }
//
//    /**
//     * @Description: 西瓜抓取
//     * @Param: [mediaUrl, cookieStr]
//     * @return: com.wmeimob.fastboot.songgong.pojo.vo.UserCartReptileVo
//     * @Author: sym
//     * @Date: 2021/6/2
//     */
//    public static UserCartReptileVo getXigua(String mediaUrl, String cookieStr) {
//        UserCartReptileVo result = new UserCartReptileVo();
//        String html = HttpUtil.get(mediaUrl,
//                null,
//                new BasicHeader[]{
//                        new BasicHeader("cookie", cookieStr),
//                });
//        // 名字
//        Pattern nicknameCompile = Pattern.compile(xigua_username_regex);
//        Matcher nicknameMatcher = nicknameCompile.matcher(html);
//        while (nicknameMatcher.find()) {
//            result.setNickName(nicknameMatcher.group(1));
//        }
//
//        // 粉丝
//        Pattern fansCompile = Pattern.compile(xigua_fans_regex);
//        Matcher fansMatcher = fansCompile.matcher(html);
//        while (fansMatcher.find()) {
//            result.setFansNum(fansMatcher.group(1));
//        }
//
//        // 头像
//        Pattern headCompile = Pattern.compile(xigua_head_regex);
//        Matcher headMatcher = headCompile.matcher(html);
//        while (headMatcher.find()) {
//            result.setHeadImgUrl(headMatcher.group(1));
//        }
//        return result;
//    }
//}
