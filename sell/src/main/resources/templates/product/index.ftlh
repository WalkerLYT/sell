<html>
<#include "../common/header.ftlh">

<body>
<div id="wrapper" class="toggled">

    <#assign shopIdCpy="${shopId}">
    <#--边栏sidebar-->
    <#include "../common/nav.ftlh">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="${(productInfo.productIcon)!''}" alt="">
                            <input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                                selected
                                            </#if>
                                        >${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                        <input hidden type="text" name="shopId" value="${(shopIdCpy)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                        <button type="button" class="btn btn-default" onclick="callBackendFunction()">语音识别</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function callBackendFunction() {
        alert("11111111");
        $.get("ajaxServlet", {username: "rose"}, function (data) {
            //servlet        parameters(request中)    data:response
            alert(data);
        }, "text");
    }
</script>

</body>
</html>

