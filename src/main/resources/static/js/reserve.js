document.addEventListener("DOMContentLoaded", initReservePage);

async function initReservePage(){
    const displayInfoId = $('#container').attr('data-displayInfoId');

    let titleArea = new TitleArea();
    let titleData = await titleArea.requestDisplayInfo(displayInfoId);
    console.log(titleData);

    titleArea.drawTitleImage(titleData);
    titleArea.drawTitleDetail(titleData);

    let booking = new Booking();
    booking.drawTicketArea(titleData.priceInfos);
}

class TitleArea{
    constructor() {
        this.titleImageSection = $('.visual_img');
        this.titleImageTemplate = $('#titleImageTemplate').html();

        this.titleDetailSection = $('.section_store_details');
        this.titleDetailTemplate = $('#titleDetailTemplate').html();

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
        let bindTemplate = Handlebars.compile(this.titleImageTemplate);
        let resultHTML = "";
        let minPrice = 1000_000_000;
        for(const price of titleData.priceInfos){
            minPrice = Math.min(minPrice,price.discountedPrice);
        }

        titleData.displayInfo.minPrice = minPrice;

        resultHTML = bindTemplate(titleData.displayInfo);

        this.titleImageSection.html(resultHTML);
    }

    drawTitleDetail(titleData){
        let bindTemplate = Handlebars.compile(this.titleDetailTemplate);
        let processedInfo = this.preprocessDisplayInfo(titleData);
        let resultHTML = bindTemplate(processedInfo);

        this.titleDetailSection.html(resultHTML);
    }

    preprocessDisplayInfo(titleData){
        let resultInfo = {};
        for(let key in titleData.displayInfo){
            resultInfo[key] = titleData.displayInfo[key];
        }
        resultInfo['priceInfos'] = titleData.priceInfos;

        return resultInfo;
    }
}

class Booking{
    constructor() {
        this.ticketSection = $('.ticket_body');
        this.ticketTemplate = $('#ticketTemplate').html();
    }

    drawTicketArea(priceInfos){
        let bindTemplate = Handlebars.compile(this.ticketTemplate);
        let resultHTML = "";

        for(let priceInfo of priceInfos){
            resultHTML+= bindTemplate(priceInfo);
        }

        this.ticketSection.html(resultHTML);
    }
}

