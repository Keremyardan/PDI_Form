package com.reysas_pdi.backend.core.config.result;

import com.reysas_pdi.backend.core.config.utilities.Msg;
import com.reysas_pdi.backend.dto.CursorResponse;
import org.springframework.data.domain.Page;

public class ResultHelper {
        public static <T> ResultData<T> created(T data) {
            return new ResultData<>(true, Msg.CREATED,"201", data);
    }

    public static <T> ResultData<T> validateError(T data) {
            return new ResultData<>(false, Msg.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> success(T data) {
            return new ResultData<>(true, Msg.OK,"200", data);
    }

    public static Result ok() {
            return new Result(true,Msg.OK,"200");
    }

public static Result notFoundError(String message) {
            return new Result(false,message, "404");
}

public static <T> ResultData<CursorResponse<T>> cursor (Page<T> pageData) {
            CursorResponse<T> cursor = new CursorResponse<>();
            cursor.setItems(pageData.getContent());
            cursor.setPage(pageData.getNumber());
            cursor.setSize(pageData.getSize());
            cursor.setTotalElements(pageData.getTotalElements());
            return ResultHelper.success(cursor);
}

public static <T> ResultData<T> EmailExists() {

            return new ResultData<>(false, Msg.VALIDATE_ERROR,"400",null);
}
}
