package jx.pgz.dao.yw.service.impl;

import cn.afterturn.easypoi.csv.CsvImportUtil;
import cn.afterturn.easypoi.csv.entity.CsvImportParams;
import jx.pgz.dao.yw.entity.Position;
import jx.pgz.dao.yw.entity.Running;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PositionServiceImplTest {


    @Test
    void test1() {
        try ( FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\pengmaofang\\Documents\\Tencent Files\\1137951878\\FileRecv\\running.csv"));){
            CsvImportParams csvImportParams=new CsvImportParams();
            List<Running> objects = CsvImportUtil.importCsv(fileInputStream, Running.class,csvImportParams );
            for (Running so : objects) {
                System.out.println(so);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void test2() {
        try ( FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\pengmaofang\\Documents\\Tencent Files\\1137951878\\FileRecv\\position.csv"));){
            CsvImportParams csvImportParams=new CsvImportParams();
            List<Position> objects = CsvImportUtil.importCsv(fileInputStream, Position.class,csvImportParams );
            for (Position so : objects) {
                System.out.println(so);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}