document.addEventListener("DOMContentLoaded", initReservePage);

async function initReservePage() {
    const displayInfoId = $('#container').attr('data-displayInfoId');

    let titleArea = new TitleArea();
    let titleData = await titleArea.requestDisplayInfo(displayInfoId);
    console.log(titleData);

    titleArea.drawTitleImage(titleData);
    titleArea.drawTitleDetail(titleData);

    let booking = new Booking();
    booking.drawTicketArea(titleData.priceInfos);
    booking.addEventListeners();

}

class TitleArea {
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

    drawTitleImage(titleData) {
        let bindTemplate = Handlebars.compile(this.titleImageTemplate);
        let resultHTML = "";
        let minPrice = 1000_000_000;
        for (const price of titleData.priceInfos) {
            minPrice = Math.min(minPrice, price.discountedPrice);
        }

        titleData.displayInfo.minPrice = minPrice;

        resultHTML = bindTemplate(titleData.displayInfo);

        this.titleImageSection.html(resultHTML);
    }

    drawTitleDetail(titleData) {
        let bindTemplate = Handlebars.compile(this.titleDetailTemplate);
        let processedInfo = this.preprocessDisplayInfo(titleData);
        let resultHTML = bindTemplate(processedInfo);

        this.titleDetailSection.html(resultHTML);
    }

    preprocessDisplayInfo(titleData) {
        let resultInfo = {};
        for (let key in titleData.displayInfo) {
            resultInfo[key] = titleData.displayInfo[key];
        }
        resultInfo['priceInfos'] = titleData.priceInfos;

        return resultInfo;
    }
}

class Booking {
    constructor() {
        this.ticketSection = $('.ticket_body');
        this.ticketTemplate = $('#ticketTemplate').html();
        this.ticketButton = $('.clearfix');

    }

    addEventListeners() {
        $('.clearfix').on("click", this.ticketSelectEvent.bind(this));
    }

    drawTicketArea(priceInfos) {
        let bindTemplate = Handlebars.compile(this.ticketTemplate);
        let resultHTML = "";

        for (let priceInfo of priceInfos) {
            resultHTML += bindTemplate(priceInfo);
        }

        this.ticketSection.html(resultHTML);
    }

    //DOM 선택이 너무 조잡함. 수정 필요.
    ticketSelectEvent() {
        let clickedTag = event.target;
        let id = clickedTag.id;
        let parentTag = clickedTag.closest('.clearfix');
        let countTag = $(parentTag).find('.count_control_input');
        let value = parseInt($(countTag).val());
        let minusButton =  $(parentTag).find('#minus');

        if (id === 'plus') {
            value+=1;
        } else if (id === 'minus') {
            value = Math.max(0,value-1);
        }

        $(countTag).val(value);

        let priceInfoSection = $(parentTag).parent().next();
        const unitPrice = parseInt($(priceInfoSection).find('.price').html());

        let totalPriceSection = $(parentTag).next();
        let totalPriceTag = $(totalPriceSection).find('.total_price');
        const totalPrice = unitPrice*value;
        totalPriceTag.html(totalPrice);

        if(value===0){
            minusButton.addClass('disabled');
            $(countTag).addClass('disabled');
            $(totalPriceSection).removeClass('on_color');
        }else{
            minusButton.removeClass('disabled');
            $(countTag).removeClass('disabled');
            $(totalPriceSection).addClass('on_color');
        }
    }
}

