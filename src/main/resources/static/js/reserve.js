document.addEventListener("DOMContentLoaded", initReservePage);

async function initReservePage(){
    //const displayInfoId =

    let titleArea = new TitleArea();
    let titleData = await titleArea.requestDisplayInfo(1);
    console.log(titleData);

    titleArea.drawTitleImage(titleData);

}

class TitleArea{
    constructor() {
        this.titleImageSection = $('.visual_img');
        this.titleTemplate = $('#titleTemplate').html();

    }

    requestDisplayInfo(displayInfoId) {
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: "/reserve/displayInfo/" + displayInfoId,
                type: "get",
                success: function (response) {
                    resolve(response);
                },
                error: function () {
                    alert("detail comment data load failed.");
                }
            });
        });
    }

    drawTitleImage(titleData){
        let bindTemplate = Handlebars.compile(this.titleTemplate);
        let resultHTML = "";
        let minPrice = 1000_000_000;
        for(const price of titleData.priceInfos){
            minPrice = Math.min(minPrice,price.discountedPrice);
        }

        titleData.displayInfo.minPrice = minPrice;

        resultHTML = bindTemplate(titleData.displayInfo);

        this.titleImageSection.html(resultHTML);
    }


}

