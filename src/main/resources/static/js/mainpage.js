$(document).ready(initMainPage());

async function initMainPage(){
    let promotionData = await getInitData();
    console.log(promotionData);

   let promotion = await new Promotion(promotionData);

}

function getInitData(){
    return new Promise(function(resolve, reject){
        $.ajax({
            url:"/api/promotions",
            type:"get",
            success:function (response){
                resolve(response);
            },
            error:function(){
                alert("data load failed.");
            }
        });
    });
}

class Promotion {
    constructor(promotionData) {
        this.promotionArea = $(".visual_img");

        this.promotionArea.css("left","0px")
        this.promotionArea.css({
            transition:"0.5s ease-in"
        });

        this.width = 414;

        this.template = $("#promotionItem").html();
        this.promotionData = promotionData;
        this.dataCount = promotionData.length;

        this.drawPromotionImages();
        this.slide();

        this.addEventListeners();

        //setInterval(this.slide,500);
        setInterval(()=>{this.slide();},1000);

    }

    addEventListeners(){

    }

    drawPromotionImages(){
        let bindTemplate = Handlebars.compile(this.template);
        let resultHTML = "";
        for(const info of this.promotionData){
            resultHTML+= bindTemplate(info);
        }

        //무한 슬라이딩에서 순환 큐 논리
        resultHTML += bindTemplate(this.promotionData[0]);

        this.promotionArea.html(resultHTML);
    }

    //setInterval의 콜백으로 넘겨진 이 메소드에서 this를 주의해야 함.
    //this가 class가 아닌  window를 가르키게 된다.
    //arrow function으로 해결할 수 있다.
    slide(){
        let left= this.promotionArea.css("left");
        let nxtLeft = parseInt(left) - this.width;

        this.promotionArea.css("left",nxtLeft+"px");

        if(parseInt(left) <= -this.dataCount * this.width){
            this.promotionArea.css({
                transition:"none"
            });

            this.promotionArea.css("left","0px");

            setTimeout(()=>{
                this.promotionArea.css({
                    transition:"0.5s ease-in"
                });
                this.promotionArea.css("left",-this.width+"px");
            },10);
        }
    }
}