//$(document).ready(initMainPage);
document.addEventListener("DOMContentLoaded", initMainPage);

async function initMainPage() {
    const promotion = new Promotion();

    let promotionData = await promotion.getPromotionData();
    promotion.drawPromotionImages(promotionData);

    const category = new Category();
    let categoryData = await category.requestCategoryData();
    category.drawCategoryTab(categoryData);

    let initialProductData = await category.requestProductData(0, 0);
    category.drawCategoryProducts(initialProductData);
}

class Promotion {
    constructor() {
        this.width = 414;
        this.promotionArea = $(".visual_img");
        this.template = $("#promotionItem").html();

        this.dataCount = 0;
        this.totalDataCount = 0;

        this.promotionArea.css("left", "0px")
        this.promotionArea.css({
            transition: "0.5s ease-in"
        });

        setInterval(() => {
            this.slide();
        }, 1000);
    }

    getPromotionData() {
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: "/api/promotions/",
                type: "get",
                success: function (response) {
                    resolve(response);
                },
                error: function () {
                    alert("promotion data load failed.");
                }
            });
        });
    }

    drawPromotionImages(promotionData) {
        let bindTemplate = Handlebars.compile(this.template);
        let resultHTML = "";
        for (const info of promotionData) {
            resultHTML += bindTemplate(info);
        }

        //무한 슬라이딩에서 순환 큐 논리
        resultHTML += bindTemplate(promotionData[0]);

        this.totalDataCount = promotionData.length;

        this.promotionArea.html(resultHTML);
    }

    //setInterval의 콜백으로 넘겨진 이 메소드에서 this를 주의해야 함.
    //this가 class가 아닌  window를 가르키게 된다.
    //arrow function으로 해결할 수 있다.
    slide() {
        let left = this.promotionArea.css("left");
        let nxtLeft = parseInt(left) - this.width;

        this.promotionArea.css("left", nxtLeft + "px");

        this.dataCount++;

        if (this.dataCount === this.totalDataCount + 1) {
            this.dataCount = 1;

            this.promotionArea.css({
                transition: "none"
            });

            this.promotionArea.css("left", "0px");

            setTimeout(() => {
                this.promotionArea.css({
                    transition: "0.5s ease-in"
                });
                this.promotionArea.css("left", -this.width + "px");
            }, 10);
        }
    }
}

class Category {
    constructor() {
        this.addEventListeners();
        this.categoryId = 0;

        this.pageNumber = [];
        this.CATEGORY_COUNT = 100;
        for (let idx = 0; idx < this.CATEGORY_COUNT; idx++) this.pageNumber[idx] = 0;

        this.categoryTemplate = $('#categoryTabTemplate').html();
        this.categoryItemTemplate = $('#itemList').html();
    }

    addEventListeners() {
        //jquery에서 이벤트 등록은 "on"으로 한다.
        $('#categoryTab').on("click", this.activateTab.bind(this));
        $('#categoryTab').on("click", this.requestInitialProductDataWrapper.bind(this));
        $('.more').on("click", this.requestProductDataWrapper.bind(this));
    }

    activateTab(target) {
        let html = target.target;
        let liTag = html.closest("li");

        $('#categoryTab').children("li").children("a").removeClass("active");
        $(liTag).children(".anchor").addClass("active");
    }

    requestCategoryData() {
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: "/api/categories",
                type: "get",
                success: function (response) {
                    resolve(response);
                },
                error: function () {
                    alert("category data load failed.");
                }
            });
        });
    }

    drawCategoryTab(categoryData) {
        let bindTemplate = Handlebars.compile(this.categoryTemplate);

        let resultHTML = "";
        for (const info of categoryData) {
            resultHTML += bindTemplate(info);
        }

        $('#categoryTab').html(resultHTML);
        $('#categoryTab').children("li:first").children("a").addClass("active");
        $('.pink').html(categoryData[this.categoryId].count + "개");
    }

    async requestInitialProductDataWrapper(target) {
        //카테고리 탭이 바뀌면 이전 탭에 그려졌던 것들은 지워져야 한다.
        this.pageNumber[this.categoryId] = 0;

        let html = target.target;
        let liTag = html.closest("li");
        this.categoryId = liTag.dataset.category;

        if (this.pageNumber[this.categoryId] === 0) {
            let productData = await this.requestProductData(this.categoryId, this.pageNumber[this.categoryId]);
            this.pageNumber[this.categoryId]++;

            this.drawInitialCategoryProducts(productData);
        }
    }

    async requestProductDataWrapper() {
        let productData = await this.requestProductData(this.categoryId, this.pageNumber[this.categoryId]);

        if (productData.length === 0) {
            $('.more').css("display", "none");
        } else {
            $('.more').css("display", "block");
        }

        this.pageNumber[this.categoryId]++;

        this.drawCategoryProducts(productData);
    }

    requestProductData(categoryId, pageNum) {
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: "/api/products/" + categoryId + "/" + pageNum,
                type: "get",
                success: function (response) {
                    resolve(response);
                },
                error: function () {
                    alert("product data load failed.");
                }
            });
        });
    }

    drawInitialCategoryProducts(productData) {
        if (productData.length === 0) return;

        let bindTemplate = Handlebars.compile(this.categoryItemTemplate);

        let leftHTML = "";
        let rightHTML = "";

        let leftCount = $('#leftItemList').children().length;
        let rightCount = $('#rightItemList').children().length;

        if (leftCount <= rightCount) {
            leftHTML += bindTemplate(productData[0]);
        }

        for (let i = 1; i < productData.length; i++) {
            let hereData = productData[i];

            if (i % 2 === 1) { //right
                rightHTML += bindTemplate(hereData);
            } else {  //left
                leftHTML += bindTemplate(hereData);
            }
        }

        $('#leftItemList').html(leftHTML);
        $('#rightItemList').html(rightHTML);
    }

    drawCategoryProducts(productData) {
        if (productData.length === 0) return;

        let bindTemplate = Handlebars.compile(this.categoryItemTemplate);

        let leftHTML = $('#leftItemList').html();
        let rightHTML = $('#rightItemList').html();

        let leftCount = $('#leftItemList').children().length;
        let rightCount = $('#rightItemList').children().length;

        if (leftCount <= rightCount) {
            leftHTML += bindTemplate(productData[0]);
        }

        for (let i = 1; i < productData.length; i++) {
            let hereData = productData[i];

            if (i % 2 === 1) { //right
                rightHTML += bindTemplate(hereData);
            } else {  //left
                leftHTML += bindTemplate(hereData);
            }
        }

        $('#leftItemList').html(leftHTML);
        $('#rightItemList').html(rightHTML);
    }

}
