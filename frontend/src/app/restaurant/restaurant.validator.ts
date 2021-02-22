import { FormControl } from '@angular/forms';

export class RestaurantValidator {
    pattern:string="[/a-zA-Z 0-9][a-zA-Z 0-9]+";
    static validRestaurantName(fc:FormControl){
        if(fc.value.match("[/a-zA-Z 0-9][a-zA-Z 0-9]+")){
                    return ({validRestaurantName:true});
        }
        else{
            return(null)
        }
    }
}