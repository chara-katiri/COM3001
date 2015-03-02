/* Used for global document, $, and Utils */
var ItemsList = (function () {

    /* Use of "use strict"; to define that JavaScript code should be executed in "strict mode" undeclared variables cannot be used..
     * Source: W3schools.com, 'JavaScript "use strict"', 2015. [Online]. Available: http://www.w3schools.com/js/js_strict.asp. [Accessed: 10- Feb- 2015].
     */
    "use strict";

    var addItem, _createItemDiv, _addFavToggleIfAuthorized, _addDeleteButton;


    addItem = function (item, addDelete) {
        var itemList = document.querySelector(".itemsList"),
                $itemsList = $(itemsList);

        if (itemsList && $itemsList) {
            itemList.appendChild(_createItemDiv(item, addDelete));
        }
    };

    /*
     * Use of DOM appendChild method to append an item as the last child of a node in a list.
     * Source: W3schools.com, 'HTML DOM appendChild() Method', 2015. [Online]. Available: http://www.w3schools.com/jsref/met_node_appendchild.asp. [Accessed: 09- Feb- 2015]. 
     */
    _createItemDiv = function (itemData, addDelete) {
        var itemDiv = document.createElement("div"), //creation of <div> node itemDiv
                nameDiv = document.createElement("div"),
                contentDiv = document.createElement("div");

        itemDiv.classList.add("item");
        itemDiv.classList.add(itemData.id);
        contentDiv.classList.add("itemData");

        nameDiv.classList.add("itemName");
        nameDiv.classList.add("btn");
        nameDiv.classList.add("btn-default");
        nameDiv.innerHTML = itemData.name;

        itemDiv.appendChild(nameDiv); //append the text to div

        $.each(itemData, function (key, value) {
            var itemImage, itemItemDiv;

            if (key === "itemImage") {
                itemImage = document.createElement("img");
                $(itemImage).attr("src", value);
                contentDiv.appendChild(itemImage);
            } else {
                itemItemDiv = document.createElement("div");
                itemItemDiv.classList.add(key);
                itemItemDiv.innerHTML = value;
                contentDiv.appendChild(itemItemDiv);
            }
        });

        _addFavToggleIfAuthorized(itemData.id, contentDiv);

        if (addDelete) {
            _addDeleteButton(itemData.id, contentDiv);
        }

        itemDiv.appendChild(contentDiv);

        return itemDiv;
    };

    _addFavToggleIfAuthorized = function (itemId, contentDiv) {
        var addToFavourites = "Add To Favourites",
                removeFromFavourites = "Remove From Favourites";

        $.ajax({
            type: 'Get',
            url: Utils.getPageContext() + '/isFavourite? item=' + itemId,
            success: function (isFavourite) {
                var authorizedToggle = document.querySelector('.authorizeUser .toggleFavourite'),
                        toggleFav;

                if (Utils.isValidVariable(authorizedToggle)) {

                    toggleFav = authorizedToggle.cloneNode(true); //deep clone

                    toggleFav.innerHTML = isFavourite ? removeFromFavourites : addToFavourites;
                    toggleFav.classList.add("btn");
                    toggleFav.classList.add("btn-default");

                    $(toggleFav).click(function () {

                        if (this.innerHTML === addToFavourites) {
                            $.ajax({
                                type: 'Get',
                                url: Utils.getPageContext() + '/addToFavourites?item=' + itemId
                            });

                            this.innerHTML = removeFromFavourites;
                        }
                        else {
                            $.ajax({
                                type: 'Get',
                                url: Utils.getPageContext() + '/removeFromFavourites? item=' + itemId,
                                success: function () {
                                    if (document.URL.indexOf("favourites") !== -1) {
                                        var items = document.querySelectorAll(".item");
                                        $.each(items, function () {
                                            if (this.classList.contains(itemId)) {
                                                this.parentElement.removeChild(this);
                                            }
                                        });
                                    }
                                }
                            });

                            this.innerHTML = addToFavourites;
                        }
                    });

                    contentDiv.appendChild(toggleFav);
                }
            }
        });
    };

    _addDeleteButton = function (itemId, contentDiv) {
        var deleteItem = document.createElement("div");

        deleteItem.classList.add("btn");
        deleteItem.classList.add("btn-default");
        deleteItem.classList.add("deleteItem");
        deleteItem.innerHTML = "Delete Item";

        $(deleteItem).click(function () {
            $.ajax({
                type: 'Post',
                headers: {
                    'X-CSRF-Token': Utils.getCsrfToken()
                },
                url: Utils.getPageContext() + '/items/manage/remove',
                data: {
                    itemId: itemId
                },
                success: function () {
                    var items = document.querySelectorAll(".item");
                    $.each(items, function () {
                        if (this.classList.contains(itemId)) {
                            this.parentElement.removeChild(this);
                        }
                    });
                }
            });
        });

        contentDiv.appendChild(deleteItem); //delete the item added
    };

    return {
        addItem: addItem
    };
}());


